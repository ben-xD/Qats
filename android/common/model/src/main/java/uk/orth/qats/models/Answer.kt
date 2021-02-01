package uk.orth.qats.models

import java.net.URL

sealed class Answer
data class MultipleChoiceTextAnswer(val text: String): Answer()
data class MultipleChoiceImageAnswer(val image: URL): Answer()

// For future multiplayer modes where users collectively agree on an answer afterwards:
// e.g. With uploaded image or free text as an answer.
//data class FreeTextAnswerOption(val text: String): AnswerOption()
//data class ImageURLAnswerOption(val imageURL: URL): AnswerOption()