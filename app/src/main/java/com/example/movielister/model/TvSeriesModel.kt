package com.example.movielister.model

import kotlinx.serialization.Serializable

@Serializable
data class TvSeriesModel(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String?,
    val backdrop_path: String?
)
