package uk.orth.qats

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import uk.orth.qats.databinding.FragmentAnswerBinding
import uk.orth.qats.databinding.FragmentQuestionBinding

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