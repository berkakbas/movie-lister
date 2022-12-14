package com.example.movielister.api

import com.example.movielister.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesAPI {
    @GET(BASE_URL + POPULAR_MOVIE + API_STR + API_KEY)
    suspend fun fetchPopularMovies(): Call<List<MovieModel>>

    @GET("$BASE_URL$MOVIE{id}/$API_STR$API_KEY")
    suspend fun fetchMovie(@Path("id") movieId: Int): Call<MovieModel>
}