package com.example.movielister.api

import com.example.movielister.model.PersonModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PersonAPI {
    @GET("$BASE_URL$PERSON{id}/$API_STR$API_KEY")
    fun fetchPersonInfo(@Path("id") id: Int): Call<PersonModel>
}