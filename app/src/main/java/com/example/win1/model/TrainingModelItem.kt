package com.example.win1.model


import com.google.gson.annotations.SerializedName

data class TrainingModelItem(
    @SerializedName("img")
    val img: String,
    @SerializedName("text")
    val text: String
)