package com.thirdwinter.gomoneyassessment.db.architecture.dao.api.response

import com.google.gson.annotations.SerializedName
import com.thirdwinter.gomoneyassessment.db.architecture.model.Competition

data class TableResponse(
    @SerializedName("competition") val competition: Competition,
    @SerializedName("standings") val standings: List<StandingsResponse>?
)