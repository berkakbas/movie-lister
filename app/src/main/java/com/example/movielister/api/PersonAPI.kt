package com.example.movielister.api

import com.example.movielister.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonAPI {
    @GET("$BASE_URL$PERSON{id}/$API_STR$API_KEY")
    suspend fun fetchPersonInfo(@Path("id") movieId: Int): Call<MovieModel>
}