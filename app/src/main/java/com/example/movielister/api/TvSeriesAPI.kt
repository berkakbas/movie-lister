package com.example.movielister.api

import com.example.movielister.model.TvSeriesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.movielister.BuildConfig.*

const val POPULAR_TV = "tv/popular/"
const val TV = "tv/"

interface TvSeriesAPI {
    @GET(BASE_URL + POPULAR_TV + API_STR + API_KEY)
    fun fetchPopularTvSeries(): Call<List<TvSeriesModel>>

    @GET("$BASE_URL$TV{id}$API_STR$API_KEY")
    fun fetchTvSerie(@Path("id") tvId: Int): Call<TvSeriesModel>
}