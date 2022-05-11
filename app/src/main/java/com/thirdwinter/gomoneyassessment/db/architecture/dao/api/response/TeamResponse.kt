package com.thirdwinter.gomoneyassessment.db.architecture.model.response

import com.google.gson.annotations.SerializedName
import com.thirdwinter.gomoneyassessment.db.architecture.model.Competition
import com.thirdwinter.gomoneyassessment.db.architecture.model.Team

data class TeamResponse(
    val competition: Competition?,
    val teams: List<Team>?
)