package com.example.movielister.model

import com.squareup.moshi.Json

data class PersonImageResponseModel(@Json(name = "profiles") val profiles: List<PersonImageModel>)