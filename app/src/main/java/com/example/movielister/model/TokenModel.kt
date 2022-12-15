package com.example.movielister.model

import com.google.gson.annotations.SerializedName

data class TokenModel(
    @SerializedName("success") val success: Boolean,
    @SerializedName("expires_at") val expires_at: String,
    @SerializedName("request_token") val request_token: String
)
