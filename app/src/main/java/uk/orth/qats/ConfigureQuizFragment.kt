package uk.orth.qats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uk.orth.qats.databinding.FragmentConfigureQuizBinding

class ConfigureQuizFragment : Fragment() {
    private val navController by lazy {findNavController()}
    lateinit var binding: FragmentConfigureQuizBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConfigureQuizBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            navController.navigateUp()
        }
    }
}