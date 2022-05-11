package com.thirdwinter.gomoneyassessment.db.architecture.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.thirdwinter.gomoneyassessment.db.architecture.model.score.Score

class ScoreConverter {
    companion object {
        @TypeConverter
        @JvmStatic
        fun fromStringToScore(value: String?): Score? {
            return if (value == null) null else Gson().fromJson(value, Score::class.java)
        }


        @TypeConverter
        @JvmStatic
        fun fromScoreToString(score: Score?): String? {
            return Gson().toJson(score)
        }
    }
}