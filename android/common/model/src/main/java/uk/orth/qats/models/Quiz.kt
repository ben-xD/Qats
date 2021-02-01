package uk.orth.qats.models


data class User(val userId: String, val nickName: String)
data class Quiz(val code: String, val participants: List<User>)

data class QuizMode(
    val questionQuantity: Int,
    val timePerQuestionInSeconds: Int
)
