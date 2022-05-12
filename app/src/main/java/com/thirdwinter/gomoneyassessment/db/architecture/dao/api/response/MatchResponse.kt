package com.thirdwinter.gomoneyassessment.db.architecture.dao.api.response

import com.google.gson.annotations.SerializedName
import com.thirdwinter.gomoneyassessment.db.architecture.model.Match


data class MatchResponse(@SerializedName("matches") val matches: List<Match>)