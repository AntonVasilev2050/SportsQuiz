package com.avv2050soft.sportsquiz.presentation

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
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
private const val RIGHT_ANSWER_REWARD = 10
private const val WRONG_ANSWER_REWARD = -10
private const val QUESTION_COUNT = 10

@AndroidEntryPoint
class GameFragment : Fragment(R.layout.fragment_game) {
    private val binding by viewBinding(FragmentGameBinding::bind)
    private val viewModel by viewModels<GameViewModel>()
    private var rewardLevel = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameDuration = arguments?.getInt(GAME_DURATION_KEY, MAX_PROGRESS)
        rewardLevel = getRewardLevel(gameDuration)


        val progressBarStep = MAX_PROGRESS / (gameDuration ?: MAX_PROGRESS)
        binding.progressBar.max = MAX_PROGRESS + progressBarStep
        binding.progressBar.progress = MAX_PROGRESS + progressBarStep

        gameDuration?.let {
            viewModel.countdown(it)
            viewModel.getQuestionsFromDb(QUESTION_COUNT)
        }
        viewModel.countStateFlow.launchAndCollectIn(viewLifecycleOwner) {
            binding.textViewCount.text = it.toString()
            binding.progressBar.progress -= progressBarStep
            if (it <= 0) {
                quizRoundOver()
            }
        }
        viewModel.quizItemsStateFlow.launchAndCollectIn(viewLifecycleOwner) { quizItems ->
            if (quizItems.isNotEmpty()) {
                var questionNumber = 0
                askQuestion(questionNumber, quizItems[questionNumber])

                binding.buttonGameNext.setOnClickListener {
                    val radioGroup = binding.radioGroup
                    val selectedRadioButton: RadioButton? =
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

    private fun getRewardLevel(gameDuration: Int?): Int {

        val rewardLevel = when (gameDuration) {
            in 0..30 -> {
                4
            }

            in 31..60 -> {
                2
            }

            in 61..120 -> {
                1
            }

            else -> {
                1
            }
        }
        return rewardLevel
    }

    private fun doScoring(selectedRadioButton: RadioButton?, quizItem: QuizItem) {
        if (selectedRadioButton != null) {
            val answer: String = selectedRadioButton.text.toString()
            gameScore += if (answer == quizItem.answerOne) {
                toastString(buildString {
                    append(getString(R.string.right_answer))
                    append(answer)
                })
                RIGHT_ANSWER_REWARD * rewardLevel
            } else {
                toastString(buildString {
                    append(getString(R.string.wrong_answer))
                    append(answer)
                })
                WRONG_ANSWER_REWARD * rewardLevel
            }
        } else {
            toastString(getString(R.string.you_have_to_choose_an_answer))
            gameScore += WRONG_ANSWER_REWARD * rewardLevel
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
            val displayedQuestionNumber = (questionNumber + 1).toString()
            textViewQuestionNumber.text = displayedQuestionNumber
            imageViewGameQuestion.setImageDrawable(null)
            if (!quizItem.imageUrl.isNullOrEmpty()) {
                Glide.with(imageViewGameQuestion.context)
                    .load(quizItem.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(requestOptions)
                    .into(imageViewGameQuestion)
            }
            textViewQuestion.text = quizItem.question
            val shuffledAnswers = listOf<String>(
                quizItem.answerOne,
                quizItem.answerTwo,
                quizItem.answerThree,
                quizItem.answerFour
            ).shuffled()

            radioButton.text = shuffledAnswers[0]
            radioButton2.text = shuffledAnswers[1]
            radioButton3.text = shuffledAnswers[2]
            radioButton4.text = shuffledAnswers[3]
        }
    }

    companion object {
        var gameScore = 0
    }
}