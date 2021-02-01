package uk.orth.qats.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import uk.orth.qats.R
import uk.orth.qats.databinding.FragmentQuestionBinding
import uk.orth.qats.models.CatImage
import uk.orth.qats.models.Question

@AndroidEntryPoint
class QuestionFragment : Fragment() {
    lateinit var binding: FragmentQuestionBinding
    private val model: QuizViewModel by activityViewModels()
    private val question = MutableLiveData<Question>()
    private val catImage = MutableLiveData<CatImage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            val questionNumber = getInt(QUESTION_NUMBER_KEY)
            GlobalScope.launch {
                model.getQuestion(questionNumber)?.apply {
                    question.value = first
                    catImage.value = second
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuestionBinding.inflate(layoutInflater)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            add<QuizBottomControlFragment>(R.id.bottom_fragment_container)
        }
        MainScope().launch {
//            val (question, catImage) = model.getNextQuestion()
//            binding.question = question
        }
        return binding.root
    }

    companion object {
        // How to prevent this key from colliding, since its just hardcoded
        private const val QUESTION_NUMBER_KEY = "IMAGE_NUMBER_KEY"
        fun newInstance(questionNumber: Int): QuestionFragment {
            val fragment = QuestionFragment()
            val bundle = Bundle().apply {
                putInt(QUESTION_NUMBER_KEY, questionNumber)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}