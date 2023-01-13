package com.example.movielister.database

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

class MovieSharedPreferences {
    companion object {
        private val PREFERENCES_TIME = "preferences_time"
        private var sharedPreferences: SharedPreferences? = null

        @Volatile
        private var instance: MovieSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context): MovieSharedPreferences = instance ?: synchronized(lock) {
            instance ?: makeSharedPreferences(context).also {
                instance = it
            }
        }

        private fun makeSharedPreferences(context: Context): MovieSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return MovieSharedPreferences()
        }
    }

    fun saveTime(time: Long) {
        sharedPreferences?.edit(commit = true) {
            putLong(PREFERENCES_TIME, time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(PREFERENCES_TIME, 0)
}