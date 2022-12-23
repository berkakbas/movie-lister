package com.example.movielister.model

import com.squareup.moshi.Json

data class MovieModel(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "overview") val overview: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Float,
    @Json(name = "poster_path") val posterPath: String,
    @Json(name = "backdrop_path") val backdrop_path: String?,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    val imageUrl: String = "https://image.tmdb.org/t/p/original/"
)
