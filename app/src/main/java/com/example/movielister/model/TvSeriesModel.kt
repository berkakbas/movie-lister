package com.example.movielister.model

import com.google.gson.annotations.SerializedName

data class TvSeriesModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdrop_path: String?
)
