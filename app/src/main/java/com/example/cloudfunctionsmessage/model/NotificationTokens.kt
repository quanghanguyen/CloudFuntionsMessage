package com.example.cloudfunctionsmessage.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationTokens (
    @SerializedName("token")
    val token : String = ""
        ) : Parcelable