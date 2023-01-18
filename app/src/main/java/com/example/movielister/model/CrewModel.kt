package com.example.movielister.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class CrewModel(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?,
    @Json(name = "credit_id") val creditId: String?,
    @Json(name = "profile_path") val profilePath: String?,
    @Json(name = "department") val department: String?,
    @Json(name = "job") val job: String?,
) : Serializable