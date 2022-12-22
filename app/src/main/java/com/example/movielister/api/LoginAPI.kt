package com.example.movielister.api

import com.example.movielister.BuildConfig.*
import com.example.movielister.model.SessionModel
import com.example.movielister.model.TokenModel
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

const val AUTH_TOKEN_NEW = "authentication/token/new/"
const val AUTH_SESSION_NEW = "authentication/session/new/"
const val AUTH_TOKEN_LOGIN = "authentication/token/validate_with_login/"

interface LoginAPI {
    @GET(AUTH_TOKEN_NEW + API_STR + API_KEY)
    fun createToken(): TokenModel

    @POST("$AUTH_SESSION_NEW{request_token}${API_STR}${API_KEY}")
    fun createSession(@Path("request_token") token: String): SessionModel

    @POST("${AUTH_TOKEN_LOGIN}{username}/{password}/{request_token}${API_STR}${API_KEY}")
    fun createSessionWithLogin(@Path("username") username: String, @Path("password") password: String, @Path("request_token") token: String): TokenModel
}