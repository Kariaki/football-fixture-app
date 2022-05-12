package com.thirdwinter.gomoneyassessment.db.architecture.dao.api.response

import com.thirdwinter.gomoneyassessment.db.architecture.model.Competition
import com.thirdwinter.gomoneyassessment.db.architecture.model.Team

data class TeamResponse(
    val competition: Competition?,
    val teams: List<Team>?
)