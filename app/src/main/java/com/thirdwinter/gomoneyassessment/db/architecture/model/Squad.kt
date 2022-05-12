package com.thirdwinter.gomoneyassessment.db.architecture.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kcoding.recyclerview_helper.SuperEntity

@Entity
data class Squad(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String?,
    val position: String?,
    var teamId: Int?
) : SuperEntity()