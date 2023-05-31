package com.avv2050soft.sportsquiz.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.avv2050soft.sportsquiz.R
import com.avv2050soft.sportsquiz.databinding.FragmentMainScreenBinding
import com.avv2050soft.sportsquiz.domain.models.QuizItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private val binding by viewBinding(FragmentMainScreenBinding::bind)
    private val viewModel: MainScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSpendPoints.setOnClickListener {
            lifecycleScope.launch {
                viewModel.insertQuizItem(
                    QuizItem(
                        0,
                        "question",
                        "First",
                        "Second",
                        "Third",
                        "Fourth",
                        "android.resource://com.avv2050soft.sportsquiz/drawable/pictures_a.xml"
                    )
                )
            }
            findNavController().navigate(R.id.action_mainScreenFragment_to_wallpapersFragment)
        }
        binding.buttonCrazy.setOnClickListener {
            setupCrazy()
        }

        binding.buttonHard.setOnClickListener {
            setupHard()
        }

        binding.buttonEasy.setOnClickListener {
            setupEasy()
        }
    }

    private fun setupCrazy() {
        findNavController().navigate(R.id.action_mainScreenFragment_to_gameFragment)
    }

    private fun setupHard() {
        findNavController().navigate(R.id.action_mainScreenFragment_to_gameFragment)
    }

    private fun setupEasy() {
        findNavController().navigate(R.id.action_mainScreenFragment_to_gameFragment)
    }
}