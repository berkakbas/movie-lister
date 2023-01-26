package com.example.movielister.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.movielister.model.MovieModel

@TypeConverters(Converters::class)
@Database(entities = [MovieModel::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}