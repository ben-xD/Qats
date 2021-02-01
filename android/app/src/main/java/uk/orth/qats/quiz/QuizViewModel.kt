package uk.orth.qats.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uk.orth.qats.models.*
import uk.orth.qats.models.Order.RANDOM
import uk.orth.qats.repository.CatImagesService
import uk.orth.qats.repository.QuizService
import uk.orth.qats.repository.Result
import java.util.*
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val catImagesService: CatImagesService,
    private val quizService: QuizService,
) : ViewModel() {
    var quiz: Quiz? = null
        private set
    private var catImages = mutableListOf<CatImage>()
    var currentQuestionNumber: Int = 0
    private val questionByNumber = mutableMapOf<Int, Question>()
    var status = MutableLiveData<String?>()

    init {
//        viewModelScope.launch {
//            prefetchCatImages(MAXIMUM_IMAGE_BUFFER_SIZE)
//        }
    }

    suspend fun startQuiz(questionQuantity: Int, timePerQuestionInSeconds: Int) {
        viewModelScope.launch {
            when (val result = quizService.startQuiz(questionQuantity, timePerQuestionInSeconds)) {
                is Result.Success -> quiz = result.data
                is Result.Error -> status.postValue("Unable to start quiz.")
            }
        }
    }

    // a live data for current question
    // make network request for answer separately (to prevent cheaters network MITM)
    suspend fun getNextQuestion(): Pair<Question, CatImage> {
        currentQuestionNumber += 1
        // TODO make network request
//        return getQuestion(currentQuestionNumber)
        val answerOptions = MultipleChoiceTextAnswerOption(
            listOf(
                "Hello",
                "Good bye",
                "3rd answer",
                "Fourth answer"
            )
        )
        return Pair(
            Question(UUID.randomUUID(), "Example Question", answerOptions),
            catImages[currentQuestionNumber - 1]
        )
    }

    suspend fun getQuestion(number: Int): Pair<Question, CatImage>? {
        if (quiz == null) {
            throw IllegalStateException("User must be in a quiz to get a question.")
        }
        quiz?.let {
            val catImage = catImages[number]
            if (catImages.size > currentQuestionNumber - MINIMUM_IMAGE_BUFFER_SIZE) {
                prefetchCatImages(currentQuestionNumber + MAXIMUM_IMAGE_BUFFER_SIZE)
            }
            if (number !in questionByNumber.keys) {
                when (val response = quizService.getQuestion(it)) {
                    is Result.Success -> {
                        questionByNumber[number] = response.data
                        return Pair(questionByNumber[number]!!, catImage)
                    }
                    is Result.Error -> status.postValue("Unable to fetch Question #${number}.")
                }

            }
        }
        return null
    }

    /**
     * Fetches additional images up to fill existing image list to a specified amount
     */
    suspend fun prefetchCatImages(total: Int) {
        val fetchCount = total - catImages.size
        viewModelScope.launch(Dispatchers.IO) {
            catImages.addAll(getRandomCatImagesWithoutGifAndHats(fetchCount))
        }
    }

    suspend fun getRandomCatImagesWithoutGifAndHats(quantity: Int): List<CatImage> {
        return withContext(Dispatchers.IO) {
            when (val response = catImagesService.getCatImages(quantity, order = RANDOM)) {
                is Result.Success -> response.data.filter {
                    !(it.imageType == ImageType.GIF && !it.categories.contains(
                        "hats"
                    ))
                }
                is Result.Error -> {
                    status.postValue("Unable to fetch images")
                    // TODO should i show an error here? edge case/ network is down
                    emptyList()
                }
            }
        }
    }

    fun showSinglePlayerSetup() {

    }

    companion object {
        const val MINIMUM_IMAGE_BUFFER_SIZE = 3
        const val MAXIMUM_IMAGE_BUFFER_SIZE = 10
    }

}