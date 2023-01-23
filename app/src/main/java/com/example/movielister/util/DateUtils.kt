package com.example.movielister.util

fun String.organizeDate(): String {
    val dates = this.split("-")
    return dates[2] + "." + dates[1] + "." + dates[0]
}