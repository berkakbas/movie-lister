package com.example.movielister.api

import com.example.movielister.BuildConfig.API_KEY
import com.example.movielister.BuildConfig.API_STR
import com.example.movielister.model.PersonModel
import com.example.movielister.network.WebService
import retrofit2.http.GET
import retrofit2.http.Path

const val PERSON = "person/"

interface PersonAPI {
    @GET("$PERSON{id}$API_STR$API_KEY")
    fun fetchPersonInfo(@Path("id") id: Int): PersonModel?
}

object ProfileNetwork {
    val profileService: PersonAPI = WebService.createRetrofit().create(PersonAPI::class.java)
}