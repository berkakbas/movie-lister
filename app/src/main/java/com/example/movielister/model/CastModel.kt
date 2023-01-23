package com.example.movielister.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class CastModel(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "profile_path") val profilePath: String?,
    @Json(name = "cast_id") val castId: Int?,
    @Json(name = "character") val character: String?,
    @Json(name = "credit_id") val creditId: String?,
    @Json(name = "order") val order: Int?,
) : Serializable
