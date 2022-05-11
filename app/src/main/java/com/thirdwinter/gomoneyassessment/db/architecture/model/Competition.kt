package com.thirdwinter.gomoneyassessment.db.architecture.model

import android.os.Parcel
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class Competition(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String?, val emblemUrl: String?, val code: String, val plan: String
)