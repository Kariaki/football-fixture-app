package com.thirdwinter.gomoneyassessment.db.architecture.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kcoding.recyclerview_helper.SuperEntity
import com.thirdwinter.gomoneyassessment.db.architecture.model.score.Score
import com.thirdwinter.gomoneyassessment.util.DateTimeUtils
import java.util.*

@Entity
data class Match(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val competition: Competition?,
    var competitionId: Int?,
    val utcDate: String?,
    val status: String?,
    val stage: String?,
    val score: Score?,
    val homeTeam: Team?,
    val awayTeam: Team?,
    val referees: List<Referee>?,
    var date: String?,
    var time: String?
):SuperEntity()