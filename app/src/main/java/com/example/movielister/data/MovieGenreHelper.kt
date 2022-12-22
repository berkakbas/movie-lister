package com.example.movielister.data

object MovieGenreHelper {
    private val genreMap = mapOf(
        Pair(28, "Action"), Pair(12, "Adventure"), Pair(16, "Animation"),
        Pair(35, "Comedy"), Pair(80, "Crime"), Pair(99, "Documentary"),
        Pair(18, "Drama"), Pair(10751, "Family"), Pair(14, "Fantasy"),
        Pair(36, "History"), Pair(27, "Horror"), Pair(10402, "Music"),
        Pair(9648, "Mystery"), Pair(10749, "Romance"), Pair(878, "Science Fiction"),
        Pair(10770, "TV Movie"), Pair(53, "Thriller"), Pair(10752, "War"),
        Pair(37, "Western")
    )

    fun genreIdsToString(ids: List<Int>): String {
        var genreText = ""
        ids.forEach {
            genreText += genreMap[it] + ", "
        }
        return genreText.dropLast(2)
    }
}