package com.example.movielister.network

import com.example.movielister.api.MoviesAPI
import com.example.movielister.model.MovieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MoviesNetwork {
    fun createMoviesAPI(): MoviesAPI {
        return WebService.createRetrofit().create(MoviesAPI::class.java)
    }
}