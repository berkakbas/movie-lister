package com.example.movielister.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
sealed class MultiSearchModel: BaseResponseModel() {
    companion object {
        const val imageURL: String = "https://image.tmdb.org/t/p/original/"
    }

    val imageUrl get() = imageURL

    @JsonClass(generateAdapter = true)
    data class MovieSearchModel(
        @SerializedName("id") val id: Int?,
        @SerializedName("title") val title: String?,
        @SerializedName("poster_path") val poster_path: String?,
        @SerializedName("media_type") val mediaType: String?,
    ) : MultiSearchModel()
    @JsonClass(generateAdapter = true)
    data class SeriesSearchModel(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("poster_path") val poster_path: String?,
        @SerializedName("media_type") val mediaType: String?,
    ) : MultiSearchModel()
    @JsonClass(generateAdapter = true)
    data class PersonSearchModel(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("profile_path") val profile_path: String?,
        @SerializedName("media_type") val mediaType: String?,
    ) : MultiSearchModel()
}



