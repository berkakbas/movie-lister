package com.example.movielister.model

import com.squareup.moshi.Json

data class AccountModel(@Json(name = "id") val id: Int, @Json(name = "username") val username: String)
