package uk.orth.qats.models

import java.net.URL
import java.util.*

data class Question(val id: UUID,
                    val text: String,
                    val answerOption: AnswerOption
)

sealed class AnswerOption
data class MultipleChoiceTextAnswerOption(val options: List<String>): AnswerOption()
data class MultipleChoiceImageAnswerOption(val options: List<URL>): AnswerOption()
