package com.example.randomstringgenerator.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.randomstringgenerator.data.respository.RandomStringRepository
import com.example.randomstringgenerator.RandomStringGeneratorApplication
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

    fun clearRecent() {
        _uiState.update { it.copy(history = emptyList()) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as RandomStringGeneratorApplication)
                HomeScreenViewModel(
                    randomStringRepository = application.randomStringRepository
                )
            }
        }
    }

}