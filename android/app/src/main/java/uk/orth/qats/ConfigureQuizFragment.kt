package uk.orth.qats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.*
import uk.orth.qats.databinding.FragmentConfigureQuizBinding
import uk.orth.qats.quiz.QuizViewModel

class ConfigureQuizFragment : Fragment() {
    private val navController by lazy { findNavController() }
    lateinit var binding: FragmentConfigureQuizBinding
    private val model: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfigureQuizBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.back.setOnClickListener { navController.navigateUp() }
        binding.start.setOnClickListener { handleStartButton() }
    }

    private fun handleStartButton() {
        val timePerQuestionInSeconds = when (binding.radiosDuration.checkedRadioButtonId) {
            R.id.radio_duration_untimed -> 0
            R.id.radio_duration_10s -> 10
            R.id.radio_duration_30s -> 30
            else -> 0
        }
        val questionQuantity = binding.edittextQuestionCount.text.toString()
        lifecycleScope.launch(Dispatchers.IO) {
            model.startQuiz(
                questionQuantity.toIntOrNull() ?: DEFAULT_QUESTION_QUANTITY,
                timePerQuestionInSeconds
            )
            model.quiz?.let {
                navController.navigate(ConfigureQuizFragmentDirections.actionConfigureQuizFragmentToQuestionFragment())
            }
        }
    }

    companion object {
        const val DEFAULT_QUESTION_QUANTITY = 5
    }
}