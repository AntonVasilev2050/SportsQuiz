package com.avv2050soft.sportsquiz.presentation

import androidx.lifecycle.ViewModel
import com.avv2050soft.sportsquiz.domain.repository.DatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: DatabaseRepository
): ViewModel() {
}