package com.thirdwinter.gomoneyassessment.db.architecture.model.response

import com.google.gson.annotations.SerializedName
import com.thirdwinter.gomoneyassessment.db.architecture.model.Competition
import com.thirdwinter.gomoneyassessment.db.architecture.model.Table

data class TableResponse(
    @SerializedName("competition") val competition: Competition,
    @SerializedName("standings") val standings: List<StandingsResponse>?
)