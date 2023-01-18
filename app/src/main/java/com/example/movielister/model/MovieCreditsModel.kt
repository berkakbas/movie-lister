package com.example.movielister.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCreditsModel(
    @Json(name = "cast") val cast: List<CastModel>,
    @Json(name = "crew") val crew: List<CrewModel>
)
