package com.avv2050soft.sportsquiz.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avv2050soft.sportsquiz.domain.models.Player
import com.avv2050soft.sportsquiz.domain.models.QuizItem
import com.avv2050soft.sportsquiz.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ONE_SEC = 1000L

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: DatabaseRepository
) : ViewModel() {

    private var count = 0
    private val _countStateFlow = MutableStateFlow(count)
    val countStateFlow = _countStateFlow

    private var quizItems: List<QuizItem> = emptyList()
    private val _quizItemsStateFlow = MutableStateFlow(quizItems)
    val quizItemsStateFlow = _quizItemsStateFlow

    fun countdown(gameDuration: Int) {
        viewModelScope.launch {
            count = gameDuration
            _countStateFlow.value = count
            while (count > 0) {
                delay(ONE_SEC)
                count--
                _countStateFlow.value = count
            }
        }
    }

    fun getQuestionsFromDb(count: Int) {
        viewModelScope.launch {
            quizItems = repository.getQuizItemsFromDb(count)
            _quizItemsStateFlow.value = quizItems
        }
    }

    fun insertPlayerIntoDb(player: Player) {
        viewModelScope.launch {
            repository.insertPlayer(player)
        }
    }

    companion object {
        var gameScore = 0
        var isTicking = false
        var questionNumber = 0
    }
}
