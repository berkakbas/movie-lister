package com.example.movielister.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import java.io.Serializable


@JsonClass(generateAdapter = true)
@Entity
data class MovieModel(
    @ColumnInfo(name = "id")
    @SerializedName("id") val id: Int?,
    @ColumnInfo(name = "title")
    @SerializedName("title") val title: String?,
    @ColumnInfo(name = "overview")
    @SerializedName("overview") val overview: String?,
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date") val release_date: String?,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average") val vote_average: Float?,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path") val poster_path: String?,
    @ColumnInfo(name = "genre_ids")
    @SerializedName("genre_ids") val genre_ids: List<Int>?,
    val imageUrl: String = "https://image.tmdb.org/t/p/original/"
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}
