package com.example.movielister.api

import com.example.movielister.BuildConfig.API_KEY
import com.example.movielister.BuildConfig.API_STR
import com.example.movielister.model.SessionModel
import com.example.movielister.model.TokenModel
import com.example.movielister.network.WebService
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

const val AUTH_TOKEN_NEW = "authentication/token/new"
const val AUTH_SESSION_NEW = "authentication/session/new"
const val AUTH_TOKEN_LOGIN = "authentication/token/validate_with_login"

interface LoginAPI {
    @GET(AUTH_TOKEN_NEW + API_STR + API_KEY)
    suspend fun createToken(): TokenModel?

    @POST("$AUTH_SESSION_NEW${API_STR}${API_KEY}")
    suspend fun createSession(@Query("request_token") token: String): SessionModel?

    //@Headers("RequireSessionToken: true")
    @POST("${AUTH_TOKEN_LOGIN}${API_STR}${API_KEY}")
    suspend fun createSessionWithLogin(@Query("username") username: String, @Query("password") password: String, @Query("request_token") token: String): TokenModel?
}

object LoginNetwork {
    val loginService: LoginAPI = WebService.createRetrofit().create(LoginAPI::class.java)
}