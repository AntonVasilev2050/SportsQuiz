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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val GAME_DURATION_KEY = "game duration key"
private const val GAME_DURATION_CRAZY = 30
private const val GAME_DURATION_HARD = 60
private const val GAME_DURATION_EASY = 120
@AndroidEntryPoint
class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {
    private val binding by viewBinding(FragmentMainScreenBinding::bind)
    private val viewModel: MainScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.initDb()
        }
        binding.buttonSpendPoints.setOnClickListener {
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

        binding.textViewPointsCount.text = GameFragment.gameScore.toString()
    }

    private fun setupCrazy() {
        val bundle = Bundle()
        bundle.putInt(GAME_DURATION_KEY, GAME_DURATION_CRAZY)
        findNavController().navigate(R.id.action_mainScreenFragment_to_gameFragment, bundle)
    }

    private fun setupHard() {
        val bundle = Bundle()
        bundle.putInt(GAME_DURATION_KEY, GAME_DURATION_HARD)
        findNavController().navigate(R.id.action_mainScreenFragment_to_gameFragment, bundle)
    }

    private fun setupEasy() {
        val bundle = Bundle()
        bundle.putInt(GAME_DURATION_KEY, GAME_DURATION_EASY)
        findNavController().navigate(R.id.action_mainScreenFragment_to_gameFragment, bundle)
    }
}