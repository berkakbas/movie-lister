package com.example.movielister.room

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromListString(list: List<Int>): String = list.toString()

    @TypeConverter
    fun toListFromString(str: String): List<Int> {
        val result = ArrayList<Int>()
        val split = str.replace("[", "").replace("]", "").replace(" ", "").split(",")
        for (n in split) {
            try {
                result.add(n.toInt())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return result
    }
}