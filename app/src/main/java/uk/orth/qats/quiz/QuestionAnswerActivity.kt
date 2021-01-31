package uk.orth.qats.quiz

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import dagger.hilt.android.AndroidEntryPoint
import uk.orth.qats.R
import uk.orth.qats.databinding.ActivityQuestionAnswerBinding

@AndroidEntryPoint
class QuestionAnswerActivity : AppCompatActivity() {
    val model: QuizViewModel by viewModels()
    private lateinit var binding: ActivityQuestionAnswerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_answer)
        supportActionBar?.hide()
        binding = ActivityQuestionAnswerBinding.inflate(layoutInflater)

//        binding.buttonNext.setOnClickListener {
//            Timber.d("Clicking next")
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<QuestionFragment>(R.id.fragment_container)
//            }
//        }

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<QuestionFragment>(R.id.fragment_container)
            }
        }
    }

    private fun showNextQuestion() {
        // get next question from server

        // fragment transaction with next question

    }

    private fun submitSelectedAnswer() {
        // Check answer
    }
}