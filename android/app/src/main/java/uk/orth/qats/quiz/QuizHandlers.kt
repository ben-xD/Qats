package uk.orth.qats.quiz

import android.view.View
import timber.log.Timber
import javax.inject.Inject

class QuizHandlers @Inject constructor() {
    fun onNextClick(view: View) {
        Timber.d("Just clicked now")
    }
}