package com.thirdwinter.gomoneyassessment.util

import android.text.format.DateUtils
import java.sql.Date
import java.sql.Timestamp
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object DateTimeUtils {


    fun getDate(utcDate: String): String {
        val split = utcDate.split("T")
        return split[0]
    }

    fun getTime(utcDate: String): String {
        val split = utcDate.split("T")
        return split[1].dropLast(4)
    }


    fun getDate(timeStamp: Long): String {
        val stamp = Timestamp(timeStamp)
        val date = Date(stamp.time)
        val f: DateFormat = SimpleDateFormat("yyyy-MM-dd")

        return f.format(date)
    }


}