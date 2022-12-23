package com.example.movielister.model

import com.squareup.moshi.Json

data class MovieResponseModel(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<MovieModel>
)
