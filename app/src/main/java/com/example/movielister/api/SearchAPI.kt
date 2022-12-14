package com.example.movielister.api

import com.example.movielister.model.MovieModel
import com.example.movielister.model.TvSeriesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchAPI {
    @GET("$BASE_URL$SEARCH_MOVIE{query}$API_STR$API_KEY")
    fun searchMovie(@Path("query") query: String): Call<List<MovieModel>>

    @GET("$BASE_URL$SEARCH_TV_SERIE{query}$API_STR$API_KEY")
    fun searchTvSerie(@Path("query") query: String): Call<List<TvSeriesModel>>
}