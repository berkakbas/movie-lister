package com.example.movielister.model

import com.squareup.moshi.Json
import java.io.Serializable

data class TvSeriesModel(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "overview") val overview: String,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "vote_average") val voteAverage: Float?,
    @Json(name = "first_air_date") val firstAirDate: String?,
    @Json(name = "genre_ids") val genreIds: List<Int>,
    val imageUrl: String = "https://image.tmdb.org/t/p/original/"
) : Serializable
