package com.example.movielister.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movielister.model.MovieModel

@Dao
interface MovieDao {
    @Insert
    suspend fun insertAll(vararg movies: MovieModel): List<Long>

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<MovieModel>

    @Query("SELECT * FROM movie WHERE uuid = :movieID")
    suspend fun getMovie(movieID: Int): MovieModel

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()

}