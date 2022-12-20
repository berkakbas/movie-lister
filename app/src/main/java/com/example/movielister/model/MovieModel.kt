package com.example.movielister.model

import com.squareup.moshi.Json

data class MovieModel(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "posterPath") val posterPath: String,
    @Json(name = "backdrop_path") val backdrop_path: String?
)
