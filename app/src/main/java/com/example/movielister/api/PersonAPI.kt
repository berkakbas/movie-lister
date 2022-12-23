package com.example.movielister.api

import com.example.movielister.BuildConfig.*
import com.example.movielister.model.PersonModel
import retrofit2.http.GET
import retrofit2.http.Path

const val PERSON = "person/"

interface PersonAPI {
    @GET("$PERSON{id}$API_STR$API_KEY")
    fun fetchPersonInfo(@Path("id") id: Int): PersonModel?
}