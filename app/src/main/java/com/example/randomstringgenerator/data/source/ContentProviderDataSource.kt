package com.example.randomstringgenerator.data.source

import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import com.example.randomstringgenerator.data.model.RandomText
import com.example.randomstringgenerator.data.model.RandomTextResponse
import com.google.gson.Gson

class ContentProviderDataSource(private val contentResolver: ContentResolver) {
    private val contentUri: Uri = Uri.parse("content://com.iav.contestdataprovider/text")
    private val gson = Gson()

    suspend fun getRandomText(length: Int): Result<RandomText> {
        return try {
            val queryArgs = Bundle().apply {
                putInt(ContentResolver.QUERY_ARG_LIMIT, length)
            }
            contentResolver.query(contentUri, null, queryArgs, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val jsonData = cursor.getString(cursor.getColumnIndex("data"))
                    val randomTextItem = gson.fromJson(jsonData, RandomTextResponse::class.java)
                    return Result.success(randomTextItem.randomText)
                }
            }
            Result.failure(Exception("Failed to get string."))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}