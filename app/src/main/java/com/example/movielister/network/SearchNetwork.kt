package com.example.movielister.network

import com.example.movielister.api.SearchAPI

object SearchNetwork {
    fun createSearchAPI(): SearchAPI {
        return WebService.createRetrofit().create(SearchAPI::class.java)
    }
}