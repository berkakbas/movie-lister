package com.example.movielister.model

import com.squareup.moshi.Json

data class PersonImageModel(
    @Json(name = "file_path") var filePath: String?,
    val imageUrl: String = "https://image.tmdb.org/t/p/original/"
)