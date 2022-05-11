package com.thirdwinter.gomoneyassessment.db.architecture.model

import com.google.gson.annotations.SerializedName

data class Referee(
    val id: Int?,
    val name: String?,
    val role: String?,
    val nationality: String?
)