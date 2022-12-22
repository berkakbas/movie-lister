package com.example.movielister.model

import com.squareup.moshi.Json

data class SessionModel(
    @Json(name = "success") val success: Boolean,
    @Json(name = "session_id") val session_id: String
)
