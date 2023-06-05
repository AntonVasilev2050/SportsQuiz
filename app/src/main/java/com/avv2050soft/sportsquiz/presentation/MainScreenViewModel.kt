package com.avv2050soft.sportsquiz.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avv2050soft.sportsquiz.data.LocalData.quizItemList
import com.avv2050soft.sportsquiz.domain.models.Player
import com.avv2050soft.sportsquiz.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: DatabaseRepository
) : ViewModel() {

    private var players : List<Player> = emptyList()
    private val _playersStateFlow = MutableStateFlow(players)
    val playerStateFlow = _playersStateFlow
    init {
//        viewModelScope.launch {
//            initDb()
//        }
    }

    suspend fun initDb() {
        quizItemList.forEach {
            repository.insertQuizItem(it)
        }
    }

    fun getAllPlayersFromDb(){
        viewModelScope.launch {
            kotlin.runCatching {
                players = repository.getAllPlayersFromDb()
            } .onSuccess {
                _playersStateFlow.value = players
            }.onFailure {
                Log.d("data_test", it.message.toString())
            }
        }
    }
}
