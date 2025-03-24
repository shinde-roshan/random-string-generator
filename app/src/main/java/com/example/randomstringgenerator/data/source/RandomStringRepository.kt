package com.example.randomstringgenerator.data.source

import com.example.randomstringgenerator.data.model.RandomText

class RandomStringRepository(private val dataSource: ContentProviderDataSource) {
    suspend fun getRandomText(length: Int): Result<RandomText> {
        return dataSource.getRandomText(length)
    }
}