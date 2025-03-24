package com.example.randomstringgenerator.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomstringgenerator.data.respository.RandomStringRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val randomStringRepository: RandomStringRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    fun fetchRandomText(length: Int) {
        _uiState.update { it.copy(isLoading = true, errorMessage = null) }

        viewModelScope.launch {
            val result = randomStringRepository.getRandomText(length)

            result.onSuccess { newItem ->
                _uiState.update { currentState ->
                    currentState.copy(
                        history = listOf(newItem) + currentState.history,
                        isLoading = false
                    )
                }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
            }
        }
    }

}