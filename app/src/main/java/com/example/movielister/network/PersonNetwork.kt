package com.example.movielister.network

import com.example.movielister.api.PersonAPI
import com.example.movielister.model.PersonModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PersonNetwork {
    private fun createPersonAPI(): PersonAPI {
        return WebService.createRetrofit().create(PersonAPI::class.java)
    }

    fun fetchPersonInfo(id: Int): PersonModel {
        val call = createPersonAPI().fetchPersonInfo(id)
        lateinit var person: PersonModel

        call.enqueue(
            object : Callback<PersonModel> {
                override fun onFailure(call: Call<PersonModel>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<PersonModel>, response: Response<PersonModel>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            person = it
                        }
                    }
                }
            })
        return person
    }

}