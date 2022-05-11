package com.thirdwinter.gomoneyassessment.db.architecture.model.score

import com.google.gson.annotations.SerializedName


data class Score(
    val winner: String?,
    val duration: String?,
    val fullTime: TeamScore?,
    val halfTime: TeamScore?,
    val extraTime: TeamScore?,
    val penalties: TeamScore?
)