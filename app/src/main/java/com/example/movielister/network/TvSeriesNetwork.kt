package com.example.movielister.network

import com.example.movielister.api.TvSeriesAPI

object TvSeriesNetwork {
    fun createTvSeriesAPI(): TvSeriesAPI {
        return WebService.createRetrofit().create(TvSeriesAPI::class.java)
    }
}