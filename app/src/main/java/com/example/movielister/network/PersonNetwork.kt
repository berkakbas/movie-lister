package com.example.movielister.network

import com.example.movielister.api.PersonAPI

object PersonNetwork {
    fun createPersonAPI(): PersonAPI {
        return WebService.createRetrofit().create(PersonAPI::class.java)
    }
}