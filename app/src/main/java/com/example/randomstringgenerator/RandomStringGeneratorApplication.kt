package com.example.randomstringgenerator

import android.app.Application
import com.example.randomstringgenerator.data.respository.RandomStringRepository
import com.example.randomstringgenerator.data.source.ContentProviderDataSource

class RandomStringGeneratorApplication: Application() {
    lateinit var randomStringRepository: RandomStringRepository

    override fun onCreate() {
        super.onCreate()
        randomStringRepository = RandomStringRepository(
            ContentProviderDataSource(contentResolver)
        )
    }
}