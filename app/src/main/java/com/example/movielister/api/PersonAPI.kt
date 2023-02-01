package com.example.movielister.api

import com.example.movielister.BuildConfig
import com.example.movielister.BuildConfig.API_KEY
import com.example.movielister.BuildConfig.API_STR
import com.example.movielister.model.PersonImageResponseModel
import com.example.movielister.model.PersonModel
import com.example.movielister.network.WebService
import retrofit2.http.GET
import retrofit2.http.Path

const val IMAGES = "images"
interface PersonAPI {
    @GET("$PERSON{person_id}${BuildConfig.API_STR}${BuildConfig.API_KEY}")
    suspend fun fetchPersonInfo(@Path("person_id") id: Int): PersonModel?

    @GET("$PERSON{person_id}${BuildConfig.API_STR}${BuildConfig.API_KEY}")
    suspend fun fetchPersonImage(@Path("person_id") id: Int): PersonModel?

    @GET("$PERSON{person_id}/$IMAGES$API_STR$API_KEY")
    suspend fun fetchImages(@Path("person_id") id: Int): PersonImageResponseModel?

}

object PersonNetwork {
    val personService: PersonAPI = WebService.createRetrofit().create(PersonAPI::class.java)
}