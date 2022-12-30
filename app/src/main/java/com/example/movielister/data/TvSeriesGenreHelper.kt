package com.example.movielister.data

object TvSeriesGenreHelper {
    private val genreMap = mapOf(
        Pair(10759, "Action & Adventure"), Pair(16, "Animation"), Pair(35, "Comedy"),
        Pair(80, "Crime"), Pair(99, "Documentary"), Pair(18, "Drama"),
        Pair(10751, "Family"), Pair(10762, "Kids"), Pair(9648, "Mystery"),
        Pair(10763, "News"), Pair(10764, "Reality"), Pair(10765, "Sci-Fi & Fantasy"),
        Pair(10766, "Soap"), Pair(10767, "Talk"), Pair(10768, "War & Politics"),
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