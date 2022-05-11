package com.thirdwinter.gomoneyassessment.db.architecture.model.response

import com.thirdwinter.gomoneyassessment.db.architecture.model.Competition

data class CompetitionResponse(val count: Int, val competitions: List<Competition>)