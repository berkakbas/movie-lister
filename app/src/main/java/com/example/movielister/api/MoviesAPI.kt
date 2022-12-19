package com.example.movielister.api

import com.example.movielister.BuildConfig.*
import com.example.movielister.model.MovieModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

const val POPULAR_MOVIE = "movie/popular/"
const val MOVIE = "movie/"

interface MoviesAPI {
    @GET(BASE_URL + POPULAR_MOVIE + API_STR + API_KEY)
    fun fetchPopularMovies(): Flow<List<MovieModel>>

    @GET("$BASE_URL$MOVIE{id}$API_STR$API_KEY")
    fun fetchMovie(@Path("id") movieId: Int): Flow<MovieModel>
}