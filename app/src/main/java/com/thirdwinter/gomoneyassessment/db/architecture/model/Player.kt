package com.thirdwinter.gomoneyassessment.db.architecture.model

data class Player(
    val id: Int?,
    val name: String?,
    val firstName: String?,
    val lastName: String?,
    val dateOfBirth: String?,
    val countryOfBirth: String?,
    val position:String,
    val shirtNumber:Int
)
