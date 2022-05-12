package com.thirdwinter.gomoneyassessment.db.architecture.dao.api.response

import com.thirdwinter.gomoneyassessment.db.architecture.model.Squad

data class SquadResponse(
    val id: Int,
    val squad: List<Squad>?
)