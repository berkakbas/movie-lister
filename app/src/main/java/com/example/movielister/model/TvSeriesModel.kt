package com.example.movielister.model

import com.squareup.moshi.Json

data class TvSeriesModel(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "backdrop_path") val backdrop_path: String?,
    @Json(name = "vote_average") val voteAverage: Float,
    val imageUrl: String = "https://image.tmdb.org/t/p/original/"
)
