package com.example.movielister.model

import com.google.gson.annotations.SerializedName

data class PersonModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val title: String,
    @SerializedName("gender") val gender: Int,
    @SerializedName("biography") val biography: String,
    @SerializedName("profile_path") val profile_path: String?
)