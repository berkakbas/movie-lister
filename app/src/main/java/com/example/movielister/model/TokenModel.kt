package com.example.movielister.model

import com.squareup.moshi.Json

data class TokenModel(
    @Json(name = "success") val success: Boolean,
    @Json(name = "expires_at") val expires_at: String,
    @Json(name = "request_token") val request_token: String
)
