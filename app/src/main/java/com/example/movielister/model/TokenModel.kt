package com.example.movielister.model

import kotlinx.serialization.Serializable

@Serializable
data class TokenModel(
    val success: Boolean,
    val expires_at: String,
    val request_token: String
)
