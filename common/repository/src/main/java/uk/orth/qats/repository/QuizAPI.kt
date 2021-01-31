package uk.orth.qats.repository

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import uk.orth.qats.models.Answer
import uk.orth.qats.models.Question
import uk.orth.qats.models.Quiz
import uk.orth.qats.models.QuizMode

interface QuizAPI {
    @GET("/quiz/{id}/question")
    suspend fun getQuestion(@Path("id") quizID: String): Response<Question>

    @POST("/question/:id/answer")
    suspend fun answerQuestion(id: String, @Body answer: Answer): Response<Question>

    @POST("/quiz/{id}")
    suspend fun joinQuiz(@Path("id") id: String): Response<Quiz> // TODO return result to user only once the user joins the game

    @POST("/quiz")
    suspend fun createQuiz(@Body quizMode: QuizMode): Response<Quiz>
}