package com.example.cloudfunctionsmessage.model

import com.google.gson.annotations.SerializedName

data class LikeModel (
    @SerializedName("user_uid")
    val user_uid : String = "",
    @SerializedName("notificationTokens")
    val notificationTokens : String = "",
    @SerializedName("author")
    val author : String = ""
        )