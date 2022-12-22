package com.example.movielister.network

import com.example.movielister.api.LoginAPI

object LoginNetwork {
    fun createLoginAPI(): LoginAPI {
        return WebService.createRetrofit().create(LoginAPI::class.java)
    }
}