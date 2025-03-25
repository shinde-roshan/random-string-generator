package com.example.randomstringgenerator.ui.home

import com.example.randomstringgenerator.data.model.RandomText

data class HomeScreenUiState(
    val history: MutableList<RandomText> = mutableListOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)