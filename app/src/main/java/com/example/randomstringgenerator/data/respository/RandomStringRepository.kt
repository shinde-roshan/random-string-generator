package com.example.randomstringgenerator.data.respository

import com.example.randomstringgenerator.data.model.RandomText
import com.example.randomstringgenerator.data.source.ContentProviderDataSource

class RandomStringRepository(private val dataSource: ContentProviderDataSource) {
    suspend fun getRandomText(length: Int): Result<RandomText> {
        return dataSource.getRandomText(length)
    }
}