package uk.orth.qats

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uk.orth.qats.ui.main.QuestionFragment

class QuestionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        prefetch the images and questions, and display them. How to reuse fragment as they will all look the same
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, QuestionFragment.newInstance())
                    .commitNow()
        }
    }
}