package com.example.movielister.model

import com.squareup.moshi.Json
import java.io.Serializable

class SearchResponseModel(
    @Json(name = "results") val results: List<MultiSearchModel>
): Serializable
