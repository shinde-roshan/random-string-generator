package com.example.randomstringgenerator.data.model

data class RandomText(
    val value: String,
    val length: Int,
    val created: String,
    var isLiked: Boolean = false
)
