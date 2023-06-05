package com.avv2050soft.sportsquiz.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.avv2050soft.sportsquiz.R
import com.avv2050soft.sportsquiz.databinding.FragmentMainScreenBinding
import com.avv2050soft.sportsquiz.presentation.GameViewModel.Companion.gameScore
import com.avv2050soft.sportsquiz.presentation.utils.launchAndCollectIn
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
            viewModel.getAllPlayersFromDb()
        }
        binding.buttonSpendPoints.setOnClickListener {
            findNavController().navigate(R.id.action_mainScreenFragment_to_wallpapersFragment)
        }
        binding.buttonCrazy.setOnClickListener {
            val gameDuration = GAME_DURATION_CRAZY
            setupGame(gameDuration)
        }

        binding.buttonHard.setOnClickListener {
            val gameDuration = GAME_DURATION_HARD
            setupGame(gameDuration)
        }

        binding.buttonEasy.setOnClickListener {
            val gameDuration = GAME_DURATION_EASY
            setupGame(gameDuration)
        }

        viewModel.playerStateFlow.launchAndCollectIn(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                gameScore = it[0].gameScore
            }
            binding.textViewPointsCount.text = gameScore.toString()
        }
    }

    private fun setupGame(gameDuration: Int) {
        val bundle = Bundle()
        bundle.putInt(GAME_DURATION_KEY, gameDuration)
        findNavController().navigate(R.id.action_mainScreenFragment_to_gameFragment, bundle)
    }
}