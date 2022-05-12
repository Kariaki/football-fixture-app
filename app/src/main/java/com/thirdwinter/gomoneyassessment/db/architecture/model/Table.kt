package com.thirdwinter.gomoneyassessment.db.architecture.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kcoding.recyclerview_helper.SuperEntity

@Entity
data class Table(
    var competitionId: Int?,
    @SerializedName("id") @PrimaryKey(autoGenerate = true) val id: Int,
    val position: Int?,
    var team: Team,
    val playedGames: Int?,
    val won: Int?,
    val draw: Int?,
    val lost: Int?,
    val points: Int?,
    val goalsFor: Int?,
    val goalsAgainst: Int?,
    val goalDifference: Int?
):SuperEntity()