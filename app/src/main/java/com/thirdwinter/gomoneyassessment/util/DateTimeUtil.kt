package com.thirdwinter.gomoneyassessment.util

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*


object DateTimeUtils {

    fun formatDate(date: Date): String {
        return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            .format(date)
    }

    fun toTimeStamp(date: String): Date? {
        return SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault()).parse(date)
    }

    fun toDate(date: String?): Date? {
        try {
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault()).parse(
                date ?: ""
            )
        } catch (e: Throwable) {

            return null
        }
    }

}