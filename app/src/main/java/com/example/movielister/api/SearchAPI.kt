package com.example.movielister.api

import com.example.movielister.BuildConfig.API_KEY
import com.example.movielister.BuildConfig.API_STR
import com.example.movielister.model.MovieResponseModel
import com.example.movielister.model.TvSeriesResponseModel
import com.example.movielister.network.WebService
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

const val SEARCH_MOVIE = "search/movie/"
const val SEARCH_TV_SERIE = "search/tv/"

interface SearchAPI {
    @GET("$SEARCH_MOVIE$API_STR$API_KEY")
    suspend fun searchMovie(@Query("query") query: String): MovieResponseModel?

    @GET("$SEARCH_TV_SERIE$API_STR$API_KEY")
    suspend fun searchTvSerie(@Query("query") query: String): TvSeriesResponseModel?
}

object SearchNetwork {
    val searchService: SearchAPI = WebService.createRetrofit().create(SearchAPI::class.java)
}