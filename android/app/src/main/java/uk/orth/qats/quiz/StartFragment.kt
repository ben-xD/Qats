package uk.orth.qats.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import uk.orth.qats.R
import uk.orth.qats.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private val navController by lazy { findNavController() }
    private lateinit var binding: FragmentStartBinding
    private val model: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.singleplayer.setOnClickListener {
            navController.navigate(StartFragmentDirections.actionStartFragmentToConfigureQuizFragment())
//            model.showSinglePlayerSetup()
        }

        Glide.with(this).load("https://scitechdaily.com/images/Cat-COVID-19-Mask.jpg")
            .fitCenter()
            .into(binding.logo)
    }
}