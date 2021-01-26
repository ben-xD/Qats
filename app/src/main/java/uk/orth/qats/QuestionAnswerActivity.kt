package uk.orth.qats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit

class QuestionAnswerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        prefetch the images and questions, and display them. How to reuse fragment as they will all look the same
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.question_answer_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<QuestionFragment>(R.id.fragment_container)
            }
        }
    }
}