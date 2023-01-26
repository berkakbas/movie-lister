package com.example.movielister.api

import com.example.movielister.BuildConfig.API_KEY
import com.example.movielister.BuildConfig.API_STR
import com.example.movielister.model.AccountModel
import com.example.movielister.model.PersonModel
import com.example.movielister.network.WebService
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val PERSON = "person/"
const val ACCOUNT = "account"


interface PersonAPI {
    @GET("$PERSON{id}$API_STR$API_KEY")
    suspend fun fetchPersonInfo(@Path("id") id: Int): PersonModel?

    @GET("$ACCOUNT$API_STR$API_KEY")
    suspend fun fetchAccountInfo(@Query("session_id") sessionId: String): AccountModel?
}

object ProfileNetwork {
    val profileService: PersonAPI = WebService.createRetrofit().create(PersonAPI::class.java)
}