package com.example.movielister.network

import com.example.movielister.BuildConfig.BASE_URL
import com.example.movielister.model.MultiSearchModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object WebService {
    fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    val factory = PolymorphicJsonAdapterFactory.of(MultiSearchModel::class.java, "media_type")
        .withSubtype(MultiSearchModel.MovieSearchModel::class.java, "movie")
        .withSubtype(MultiSearchModel.SeriesSearchModel::class.java, "tv")
        .withSubtype(MultiSearchModel.PersonSearchModel::class.java, "person")

    private val moshi = Moshi.Builder()
        .add(factory)
        .add(KotlinJsonAdapterFactory())
        .build()

}