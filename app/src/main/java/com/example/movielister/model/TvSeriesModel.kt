package com.example.movielister.model

import com.squareup.moshi.Json

data class TvSeriesModel(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "posterPath") val posterPath: String?,
    @Json(name = "backdrop_path") val backdrop_path: String?
)
