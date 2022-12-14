package com.example.movielister.network

import com.example.movielister.api.SearchAPI
import com.example.movielister.model.MovieModel
import com.example.movielister.model.TvSeriesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SearchNetwork {
    private fun createSearchAPI(): SearchAPI {
        return WebService.createRetrofit().create(SearchAPI::class.java)
    }

    fun searchMovie(query: String): List<MovieModel> {
        val call = createSearchAPI().searchMovie(query)
        var searchResult = listOf<MovieModel>()

        call.enqueue(
            object : Callback<List<MovieModel>> {
                override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<List<MovieModel>>, response: Response<List<MovieModel>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            searchResult = it
                        }
                    }
                }
            })
        return searchResult
    }

    fun searchTvSerie(query: String): List<TvSeriesModel> {
        val call = createSearchAPI().searchTvSerie(query)
        var searchResult = listOf<TvSeriesModel>()

        call.enqueue(
            object : Callback<List<TvSeriesModel>> {
                override fun onFailure(call: Call<List<TvSeriesModel>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<List<TvSeriesModel>>, response: Response<List<TvSeriesModel>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            searchResult = it
                        }
                    }
                }
            })
        return searchResult
    }
}