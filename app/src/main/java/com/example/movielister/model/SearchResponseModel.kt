package com.example.movielister.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
class SearchResponseModel(
    @Json(name = "results") val results: List<MultiSearchModel>
): Serializable
