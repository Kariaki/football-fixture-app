package com.thirdwinter.gomoneyassessment.db.architecture.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thirdwinter.gomoneyassessment.db.architecture.model.Referee

class RefereeConverter {
    companion object {

        @TypeConverter
        @JvmStatic
        fun fromStringToReferee(value: String?): Referee? {
            return if (value == null) null else Gson().fromJson(value, Referee::class.java)
        }

        @TypeConverter
        @JvmStatic
        fun fromRefereeToString(score: Referee?): String? {
            return Gson().toJson(score)
        }

        @TypeConverter
        @JvmStatic
        fun fromStringToRefereeList(value: String?): List<Referee>? {
            return if (value == null) null else Gson().fromJson(value, object: TypeToken<List<Referee>>() {}.type)
        }


        @TypeConverter
        @JvmStatic
        fun fromRefereeListToString(score: List<Referee>?): String? {
            return Gson().toJson(score)
        }
    }
}