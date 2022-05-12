package com.thirdwinter.gomoneyassessment.db.architecture.dao.api.response

import com.thirdwinter.gomoneyassessment.db.architecture.model.Competition
import com.thirdwinter.gomoneyassessment.db.architecture.model.Scorer

data class ScorersResponse (val competition: Competition,val scorers:List<Scorer>)