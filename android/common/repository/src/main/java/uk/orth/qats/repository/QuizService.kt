package uk.orth.qats.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import uk.orth.qats.models.Answer
import uk.orth.qats.models.Question
import uk.orth.qats.models.Quiz
import uk.orth.qats.models.QuizMode
import uk.orth.qats.repository.utilities.createResult
import javax.inject.Inject

class QuizService @Inject constructor() {
    private val retrofit: Retrofit
    private val api: QuizAPI// TODO make quiz a live data?

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
        api = retrofit.create(QuizAPI::class.java)
    }

    suspend fun startQuiz(
        questionQuantity: Int = 0,
        timePerQuestionInSeconds: Int = 0
    ): Result<Quiz> {
        val mode = QuizMode(questionQuantity, timePerQuestionInSeconds)
        return withContext(Dispatchers.IO) { api.createQuiz(mode).createResult() }
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

    companion object {
        const val BASE_URL = "https://qats.orth.uk/"
    }
}

