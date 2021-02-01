package uk.orth.qats.quiz

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import uk.orth.qats.R
import uk.orth.qats.databinding.ActivityAppBinding
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {
    private val navController by lazy { findNavController(R.id.nav_host_fragment) }
    private val model: QuizViewModel by viewModels()
    private lateinit var binding: ActivityAppBinding
    @Inject lateinit var quizHandlers: QuizHandlers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        supportActionBar?.hide()
        binding = ActivityAppBinding.inflate(layoutInflater)
        binding.handler = quizHandlers

        model.status.observe(this, {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                model.status.value = null
            }
        })
    }

    private fun showNextQuestion() {
        GlobalScope.launch {
            // get next question from server
            val question = model.getNextQuestion()
            // fragment transaction with next question
            // TODO use navigation to change frag
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<QuestionFragment>(R.id.fragment_container)
//                // pass image number and question number
//            }
        }

    }

    private fun showAnswerToCurrentQuestion() {

    }

    private fun submitSelectedAnswer() {
        // Check answer
    }
}