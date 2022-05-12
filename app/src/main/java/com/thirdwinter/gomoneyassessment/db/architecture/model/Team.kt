package com.thirdwinter.gomoneyassessment.db.architecture.model

import android.os.Parcel
import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.kcoding.recyclerview_helper.SuperEntity


@Entity
data class Team(
    var competitionId: Int?,
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String?,
    val crestUrl: String?,
    val shortName: String?,
    val tla: String?
) : SuperEntity(), Parcelable {
    constructor(source: Parcel) : this(
        source.readValue(Int::class.java.classLoader) as Int?,
        source.readValue(Int::class.java.classLoader) as Int,
        source.readString(),
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeValue(competitionId)
        writeValue(id)
        writeString(name)
        writeString(crestUrl)
        writeString(shortName)
        writeString(tla)
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Team>() {
            override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
                return newItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
                return newItem == oldItem
            }

        }

        @JvmField
        val CREATOR: Parcelable.Creator<Team> = object : Parcelable.Creator<Team> {
            override fun createFromParcel(source: Parcel): Team = Team(source)
            override fun newArray(size: Int): Array<Team?> = arrayOfNulls(size)
        }
    }
}