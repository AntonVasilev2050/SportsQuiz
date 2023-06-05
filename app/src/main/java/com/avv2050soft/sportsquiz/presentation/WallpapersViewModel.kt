package com.avv2050soft.sportsquiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avv2050soft.sportsquiz.domain.models.Player
import com.avv2050soft.sportsquiz.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WallpapersViewModel @Inject constructor(
private val repository: DatabaseRepository
): ViewModel() {

    fun insertPlayerIntoDb(player: Player){
        viewModelScope.launch {
            repository.insertPlayer(player)
        }
    }
}