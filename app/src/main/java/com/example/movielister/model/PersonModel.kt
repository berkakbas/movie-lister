package com.example.movielister.model

import com.squareup.moshi.Json
import java.io.Serializable

data class PersonModel(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String,
    @Json(name = "gender") val gender: Int,
    @Json(name = "biography") val biography: String,
    @Json(name = "profile_path") val profile_path: String?,
    val imageUrl: String = "https://image.tmdb.org/t/p/original/"
): Serializable