package com.thirdwinter.gomoneyassessment.util

import junit.framework.TestCase
import org.junit.Test

class DateTimeUtilsTest : TestCase() {

    @Test
    fun checkCurrentDate() {
        val currentDate = System.currentTimeMillis()
        val date = DateTimeUtils.getDate(currentDate)
        //yyyy-MM-dd
        val check = "2022-05-11"
        assertEquals("hello", "hello")


    }
}