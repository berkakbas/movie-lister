package com.example.movielister.helper

object HelperFunctions {
    fun organizeDate(rawDate: String): String {
        val dates = rawDate.split("-")
        return dates[2] + "." + dates[1] + "." + dates[0]
    }
}