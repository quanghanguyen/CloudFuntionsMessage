package com.example.cloudfunctionsmessage.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TitleModel(
    @SerializedName("token")
    val token : String = "",
    @SerializedName("title")
    val title : String = "",
    @SerializedName("author")
    val author : String = ""
) : Parcelable