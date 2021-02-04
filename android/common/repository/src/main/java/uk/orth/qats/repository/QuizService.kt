package uk.orth.qats.repository

import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.orth.qats.models.Answer
import uk.orth.qats.models.Question
import uk.orth.qats.models.Quiz
import uk.orth.qats.models.QuizMode
import uk.orth.qats.repository.utilities.EnumFactory
import uk.orth.qats.repository.utilities.createResult
import java.util.logging.Level
import java.util.logging.Logger.getLogger
import javax.inject.Inject

private const val BASE_URL = "https://qats.orth.uk/"
private val TAG = QuizService::class.java.name
private val logger = getLogger(TAG)

class QuizService @Inject constructor() {
    private val retrofit: Retrofit
    private val api: QuizAPI// TODO make quiz a live data?

    init {
        val gson = GsonBuilder().serializeNulls()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(EnumFactory())
            .build()
        api = retrofit.create(QuizAPI::class.java)
    }

    suspend fun startQuiz(
        questionQuantity: Int = 0,
        timePerQuestionInSeconds: Int = 0
    ): Result<Quiz> {
        val mode = QuizMode(questionQuantity, timePerQuestionInSeconds)
        val mediaType = MediaType.parse("text/plain")
        return try {
            val requestBody = RequestBody.create(mediaType, mode.toString())
            api.createQuiz(requestBody).createResult()
        } catch (e: Exception) {
            logger.log(Level.WARNING, e.toString())
            e.createResult()
        }
    }

    suspend fun joinQuiz(quizID: String): Result<Quiz> {
        return withContext(Dispatchers.IO) { api.joinQuiz(quizID).createResult() }
    }

    // If timed, FCMs will send the message instead.
    suspend fun getQuestion(quiz: Quiz): Result<Question> {
        return withContext(Dispatchers.IO) { api.getQuestion(quiz.code).createResult() }
    }

    /**
     * When a timed-question is retrieved, sending an answer late will 400.
     * I do this in the backend with serverless triggers.
     *
     * Users will receive an FCM/ APNs notification with answer once all users submit, or timer ends.
     */
    fun submitAnswer(answer: Answer, quiz: Quiz, questionID: String) {

    }
}

