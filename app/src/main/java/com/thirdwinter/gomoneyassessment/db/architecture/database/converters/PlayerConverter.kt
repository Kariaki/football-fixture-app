package com.thirdwinter.gomoneyassessment.db.architecture.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.thirdwinter.gomoneyassessment.db.architecture.model.Player
import com.thirdwinter.gomoneyassessment.db.architecture.model.score.Score

class PlayerConverter {

    companion object {
        @TypeConverter
        @JvmStatic
        fun fromStringToPlayer(value: String?): Player? {
            return if (value == null) null else Gson().fromJson(value, Player::class.java)
        }


        @TypeConverter
        @JvmStatic
        fun fromScoreToString(score: Player?): String? {
            return Gson().toJson(score)
        }
    }
}