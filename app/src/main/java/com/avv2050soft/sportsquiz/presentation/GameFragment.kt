package com.avv2050soft.sportsquiz.presentation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.avv2050soft.sportsquiz.R
import com.avv2050soft.sportsquiz.databinding.FragmentGameBinding
import com.avv2050soft.sportsquiz.domain.models.QuizItem
import com.avv2050soft.sportsquiz.presentation.utils.launchAndCollectIn
import com.avv2050soft.sportsquiz.presentation.utils.toastString
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

private const val MAX_PROGRESS = 10000000

@AndroidEntryPoint
class GameFragment : Fragment(R.layout.fragment_game) {
    private val binding by viewBinding(FragmentGameBinding::bind)
    private val viewModel by viewModels<GameViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameDuration = arguments?.getInt(GAME_DURATION_KEY, MAX_PROGRESS)
        val progressBarStep = MAX_PROGRESS / (gameDuration ?: MAX_PROGRESS)
        binding.progressBar.max = MAX_PROGRESS + progressBarStep
        binding.progressBar.progress = MAX_PROGRESS + progressBarStep

        gameDuration?.let {
            viewModel.countdown(it)
            viewModel.getQuestionsFromDb()
        }
        viewModel.countStateFlow.launchAndCollectIn(viewLifecycleOwner) {
            binding.textViewCount.text = it.toString()
            binding.progressBar.progress -= progressBarStep
        }
        viewModel.quizItemsStateFlow.launchAndCollectIn(viewLifecycleOwner) { quizItems ->
            if (quizItems.isNotEmpty()) {
                var questionNumber = 0
                askQuestion(questionNumber, quizItems[questionNumber])

                binding.buttonGameNext.setOnClickListener {
                    val radioGroup = binding.radioGroup
                    val selectedRadioButton: RadioButton =
                        view.findViewById(radioGroup.checkedRadioButtonId)
                    doScoring(selectedRadioButton, quizItems[questionNumber])
                    radioGroup.clearCheck()

                    if (questionNumber < quizItems.lastIndex) {
                        questionNumber++
                        askQuestion(questionNumber, quizItems[questionNumber])
                    } else {
                        quizRoundOver()
                    }
                }
            }
        }
    }

    private fun doScoring(selectedRadioButton: RadioButton, quizItem: QuizItem) {

        val answer: String = selectedRadioButton.text.toString()
        if (answer == quizItem.answerOne) {
        toastString("Right: $answer")
            gameScore += 10
        } else {
            toastString("Wrong: $answer")
            gameScore -= 10
        }

    }

    private fun quizRoundOver() {
        toastString("This quiz is over")
        findNavController().navigate(R.id.action_gameFragment_to_mainScreenFragment)
    }

    private fun askQuestion(questionNumber: Int, quizItem: QuizItem) {
        val pictureSize = resources.displayMetrics.widthPixels / 2
        val requestOptions = RequestOptions()
            .override(pictureSize, pictureSize)
            .centerCrop()
        with(binding) {
            textViewQuestionNumber.text = (questionNumber + 1).toString()
            imageViewGameQuestion.setImageDrawable(null)
            if (!quizItem.imageUrl.isNullOrEmpty()) {
                Glide.with(imageViewGameQuestion.context)
                    .load(quizItem.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(requestOptions)
                    .into(imageViewGameQuestion)
            }
            textViewQuestion.text = quizItem.question
            radioButton.text = quizItem.answerOne
            radioButton2.text = quizItem.answerTwo
            radioButton3.text = quizItem.answerThree
            radioButton4.text = quizItem.answerFour
        }
    }

    companion object {
        var gameScore = 0
    }
}