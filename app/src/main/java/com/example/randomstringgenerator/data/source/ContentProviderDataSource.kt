package com.example.randomstringgenerator.data.source

import android.content.ContentResolver
import android.net.Uri

class ContentProviderDataSource(private val contentResolver: ContentResolver) {
    private val contentUri: Uri = Uri.parse("content://com.example.contestdataprovider/text")

    suspend fun getRandomText(length: Int): String {
        return ""
    }
}