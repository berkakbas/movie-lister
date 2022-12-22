package com.example.movielister.api

import com.example.movielister.BuildConfig.API_KEY
import com.example.movielister.BuildConfig.API_STR
import com.example.movielister.model.TvSeriesModel
import retrofit2.http.GET
import retrofit2.http.Path

const val POPULAR_TV = "tv/popular/"
const val TV = "tv/"

interface TvSeriesAPI {
    @GET(POPULAR_TV + API_STR + API_KEY)
    fun fetchPopularTvSeries(): List<TvSeriesModel>

    @GET("$TV{id}$API_STR$API_KEY")
    fun fetchTvSerie(@Path("id") tvId: Int): TvSeriesModel
}