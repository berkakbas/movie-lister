package com.example.movielister.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movielister.model.MovieModel

@Dao
interface MovieDao {
    @Query("SELECT * FROM moviemodel")
    suspend fun getAllMovies(): List<MovieModel>

    @Query("SELECT * FROM moviemodel WHERE uuid = :movieId")
    suspend fun getMovie(movieId : Int) : MovieModel

    @Query("DELETE FROM moviemodel")
    suspend fun deleteAllMovies()

    @Insert
    suspend fun insertAll(vararg movies: MovieModel)
}