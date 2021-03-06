package uk.orth.qats

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import uk.orth.qats.databinding.FragmentConfigureQuizBinding
import uk.orth.qats.quiz.QuizViewModel
import uk.orth.qats.repository.Result

private val TAG = ConfigureQuizFragment::class.java.name

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
        val questionQuantity =
            binding.edittextQuestionCount.text.toString().toIntOrNull() ?: DEFAULT_QUESTION_QUANTITY

        lifecycleScope.launch {
            when (val result = model.startQuiz(questionQuantity, timePerQuestionInSeconds)) {
                is Result.Success -> navController.navigate(ConfigureQuizFragmentDirections.actionConfigureQuizFragmentToQuestionFragment())
                is Result.Error -> {
                    // long snackbar with try again button?
                    Log.w(TAG, result.exception)
                    model.toastMessage.postValue("We had a problem starting that quiz.")
                }
            }
        }
    }

    companion object {
        const val DEFAULT_QUESTION_QUANTITY = 5
    }
}