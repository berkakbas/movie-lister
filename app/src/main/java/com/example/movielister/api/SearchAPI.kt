package com.example.movielister.api

import com.example.movielister.BuildConfig.API_KEY
import com.example.movielister.BuildConfig.API_STR
import com.example.movielister.model.MovieModel
import com.example.movielister.model.TvSeriesModel
import retrofit2.http.GET
import retrofit2.http.Path

const val SEARCH_MOVIE = "search/movie/"
const val SEARCH_TV_SERIE = "search/tv/"

interface SearchAPI {
    @GET("$SEARCH_MOVIE{query}$API_STR$API_KEY")
    fun searchMovie(@Path("query") query: String): List<MovieModel>?

    @GET("$SEARCH_TV_SERIE{query}$API_STR$API_KEY")
    fun searchTvSerie(@Path("query") query: String): List<TvSeriesModel>?
}