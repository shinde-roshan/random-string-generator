package com.example.randomstringgenerator.ui.home

import com.example.randomstringgenerator.data.model.RandomText

data class HomeScreenUiState(
    val history: List<RandomText> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)