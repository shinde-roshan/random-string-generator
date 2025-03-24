package com.example.randomstringgenerator.data.source

import android.content.ContentResolver
import android.net.Uri
import com.google.gson.Gson

class ContentProviderDataSource(private val contentResolver: ContentResolver) {
    private val contentUri: Uri = Uri.parse("content://com.example.contestdataprovider/text")
    private val gson = Gson()

    suspend fun getRandomText(length: Int): String {
        return ""
    }
}