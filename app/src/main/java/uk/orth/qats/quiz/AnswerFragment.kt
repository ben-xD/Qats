package uk.orth.qats.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import uk.orth.qats.databinding.FragmentAnswerBinding

@AndroidEntryPoint
class AnswerFragment : Fragment() {
    private lateinit var binding: FragmentAnswerBinding
    private val model: QuizViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAnswerBinding.inflate(layoutInflater)
        super.onViewCreated(view, savedInstanceState)
    }
}