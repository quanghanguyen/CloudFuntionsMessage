package com.example.cloudfunctionsmessage

import com.google.gson.annotations.SerializedName

data class TitleModel(
    @SerializedName("title")
    val title : String,
    @SerializedName("author")
    val author : String
)