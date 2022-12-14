package com.example.movielister.model

import com.google.gson.annotations.SerializedName

data class SessionModel(
    @SerializedName("success") val success: Boolean,
    @SerializedName("session_id") val session_id: String
)
