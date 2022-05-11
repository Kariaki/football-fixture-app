package com.thirdwinter.gomoneyassessment.db.architecture.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Scorers")
data class Scorer(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val player: Player,
    var competitionId:Int?,
    val team: Team,
    val numberOfGoals: Int?
)