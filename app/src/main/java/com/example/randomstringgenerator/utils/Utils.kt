package com.example.randomstringgenerator.utils

import java.text.SimpleDateFormat
import java.util.Locale

private val responseDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
private val displayDateFormat = SimpleDateFormat("dd MMM, yyyy hh:mm a", Locale.getDefault())

fun resDateToDisplayDate(resDate: String): String {
    return try {
        val date = responseDateFormat.parse(resDate)
        if (date != null)
        {
            displayDateFormat.format(date)
        } else ""
    } catch (e: Exception) {
        ""
    }
}