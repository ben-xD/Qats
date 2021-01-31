package uk.orth.qats.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uk.orth.qats.repository.CatImage
import uk.orth.qats.repository.CatImagesService
import uk.orth.qats.repository.QuizService
import uk.orth.qats.models.Question
import uk.orth.qats.models.Quiz
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val catImagesService: CatImagesService,
    private val quizService: QuizService,
) : ViewModel() {
    private val quiz: Quiz? = null
    private var catImages = mutableListOf<CatImage>()
    private var currentQuestionNumber: Int = 0
    private val questionByNumber = MutableLiveData<Map<Int, Question>>()

    // a live data for current question
    // make network request for answer separately (to prevent cheaters network MITM)
    suspend fun getNextQuestion(): Pair<Question, CatImage> {
        currentQuestionNumber += 1
        return getQuestion(currentQuestionNumber)
    }

    suspend fun getQuestion(number: Int): Pair<Question, CatImage> {
        if (quiz == null) {
            throw IllegalStateException("User must be in a quiz to get a question.")
        }
        val catImage = catImages[number]
        if (catImages.size > currentQuestionNumber - MINIMUM_IMAGE_BUFFER_SIZE) {
            prefetchCatImages(currentQuestionNumber + MAXIMUM_IMAGE_BUFFER_SIZE)
        }
        return Pair(quizService.getQuestion(quiz), catImage)
    }

    /**
     * Fetches additional images up to fill existing image list to a specified amount
     */
    suspend fun prefetchCatImages(total: Int) {
        val fetchCount = total - catImages.size
        viewModelScope.launch(Dispatchers.IO) {
            catImages.addAll(catImagesService.getRandomCatImagesWithoutGifAndHats(fetchCount))
        }
    }

    companion object {
        const val MINIMUM_IMAGE_BUFFER_SIZE = 3
        const val MAXIMUM_IMAGE_BUFFER_SIZE = 10
    }

}