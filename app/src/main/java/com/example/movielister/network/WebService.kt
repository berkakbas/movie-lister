package com.example.movielister.network

import com.example.movielister.BuildConfig.BASE_URL
import retrofit2.*

object WebService {
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
    }
}