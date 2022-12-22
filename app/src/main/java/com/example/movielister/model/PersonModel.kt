package com.example.movielister.model

import com.squareup.moshi.Json

data class PersonModel(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "gender") val gender: Int,
    @Json(name = "biography") val biography: String,
    @Json(name = "profile_path") val profile_path: String?
)