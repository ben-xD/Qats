package uk.orth.qats.quiz

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import uk.orth.qats.R
import uk.orth.qats.databinding.ActivityAppBinding
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : AppCompatActivity() {
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
}