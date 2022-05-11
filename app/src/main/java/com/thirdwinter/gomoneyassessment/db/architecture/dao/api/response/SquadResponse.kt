package com.thirdwinter.gomoneyassessment.db.architecture.model.response

import com.google.gson.annotations.SerializedName
import com.thirdwinter.gomoneyassessment.db.architecture.model.Squad

data class SquadResponse(
    val id: Int,
    val squad: List<Squad>?
)