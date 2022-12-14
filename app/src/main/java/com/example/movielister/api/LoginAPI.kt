package com.example.movielister.api

import com.example.movielister.model.SessionModel
import com.example.movielister.model.TokenModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginAPI {
    @GET(BASE_URL + AUTH_TOKEN_NEW + API_STR + API_KEY)
    fun createToken(): Call<TokenModel>

    @POST("$BASE_URL$AUTH_SESSION_NEW{request_token}$API_STR$API_KEY")
    fun createSession(@Path("request_token") token: String): Call<SessionModel>

    @POST("$BASE_URL$AUTH_TOKEN_LOGIN{username}/{password}/{request_token}$API_STR$API_KEY")
    fun createSessionWithLogin(@Path("username") username: String, @Path("password") password: String, @Path("request_token") token: String): Call<TokenModel>
}