package com.example.movielister.model

import kotlinx.serialization.Serializable

@Serializable
data class SessionModel(
    val success: Boolean,
    val session_id: String
)
