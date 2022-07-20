package com.example.cloudfunctionsmessage.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TitleModel(
    @SerializedName("user_uid")
    val user_uid : String = "",
    @SerializedName("notificationTokens")
    val notificationTokens : NotificationTokens? = null,
    @SerializedName("title")
    val title : String = "",
    @SerializedName("author")
    val author : String = ""
) : Parcelable