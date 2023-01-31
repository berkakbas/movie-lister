package com.example.movielister.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

sealed class MultiSearchModel: Serializable {

    companion object {
        const val imageURL: String = "https://image.tmdb.org/t/p/original/"
    }

    val imageUrl get() = imageURL
}

data class MovieSearchModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("poster_path") val poster_path: String?,
) : MultiSearchModel()

data class SeriesSearchModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("poster_path") val poster_path: String?,
) : MultiSearchModel()

data class PersonSearchModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("profile_path") val profile_path: String?,
) : MultiSearchModel()
