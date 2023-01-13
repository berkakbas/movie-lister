package com.example.movielister.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class MovieModel(
    @ColumnInfo(name = "id")
    @Json(name = "id") val id: Int,
    @ColumnInfo(name = "title")
    @Json(name = "title") val title: String,
    @ColumnInfo(name = "overview")
    @Json(name = "overview") val overview: String,
    @ColumnInfo(name = "release_date")
    @Json(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "vote_average")
    @Json(name = "vote_average") val voteAverage: Float,
    @ColumnInfo(name = "poster_path")
    @Json(name = "poster_path") val posterPath: String?,
    @ColumnInfo(name = "backdrop_path")
    @Json(name = "backdrop_path") val backdrop_path: String?,
    @ColumnInfo(name = "genre_ids")
    @Json(name = "genre_ids") val genreIds: List<Int>,
    val imageUrl: String = "https://image.tmdb.org/t/p/original/") {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}

