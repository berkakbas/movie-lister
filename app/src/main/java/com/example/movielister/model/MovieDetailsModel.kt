package com.example.movielister.model

import com.squareup.moshi.Json
import java.io.Serializable

data class MovieDetailsModel(
    @Json(name = "runtime") val runtime: Int?,
    @Json(name = "title") val title: String?
) : Serializable
