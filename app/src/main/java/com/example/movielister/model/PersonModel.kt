package com.example.movielister.model

import kotlinx.serialization.Serializable

@Serializable
data class PersonModel(
    val id: Int,
    val title: String,
    val gender: Int,
    val biography: String,
    val profile_path: String?
)