package uk.orth.qats.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.orth.qats.databinding.FragmentQuestionBinding
import uk.orth.qats.models.Question
import java.util.UUID.randomUUID

@AndroidEntryPoint
class QuestionFragment : Fragment() {
    lateinit var binding: FragmentQuestionBinding
    private val model: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionBinding.inflate(layoutInflater)
        // show loading indicator in question.
        binding.question = model.getRandomQuestion()
        binding.question = Question(randomUUID(), "Example Question", listOf("Hello", "Good bye"))
        return binding.root
    }
}