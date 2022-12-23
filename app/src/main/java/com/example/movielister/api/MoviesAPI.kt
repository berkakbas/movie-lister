package com.example.movielister.api

import com.example.movielister.BuildConfig.*
import com.example.movielister.model.MovieModel
import com.example.movielister.model.MovieResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

const val POPULAR_MOVIE = "movie/popular/"
const val MOVIE = "movie/"

interface MoviesAPI {
    @GET(POPULAR_MOVIE + API_STR + API_KEY)
    suspend fun fetchPopularMovies(): MovieResponseModel?

    @GET("$MOVIE{id}$API_STR$API_KEY")
    suspend fun fetchMovie(@Path("id") movieId: Int): MovieModel?
}