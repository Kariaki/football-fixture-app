package com.thirdwinter.gomoneyassessment.db.architecture.dao.api.response

import com.google.gson.annotations.SerializedName
import com.thirdwinter.gomoneyassessment.db.architecture.model.Table


data class StandingsResponse(@SerializedName("table") val table: List<Table>?)