package com.thirdwinter.gomoneyassessment.db.architecture.dao.api.response

import com.thirdwinter.gomoneyassessment.db.architecture.model.Competition

data class CompetitionResponse(val count: Int, val competitions: List<Competition>)