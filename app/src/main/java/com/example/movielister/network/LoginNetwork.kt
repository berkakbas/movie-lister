package com.example.movielister.network

import com.example.movielister.api.LoginAPI
import com.example.movielister.model.TokenModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LoginNetwork {
    private fun createLoginAPI(): LoginAPI {
        return WebService.createRetrofit().create(LoginAPI::class.java)
    }

    fun createToken(): TokenModel? {
        val call = createLoginAPI().createToken()
        var token: TokenModel? = null

        call.enqueue(object : Callback<TokenModel> {
            override fun onResponse(call: Call<TokenModel>, response: Response<TokenModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        token = it
                    }
                }
            }

            override fun onFailure(call: Call<TokenModel>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return token
    }
}