package com.example.movielister.model

import kotlinx.serialization.Serializable

@Serializable
data class MovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String,
    val backdrop_path: String?
)
