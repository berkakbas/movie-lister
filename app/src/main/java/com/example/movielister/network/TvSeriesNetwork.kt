package com.example.movielister.network

import com.example.movielister.api.TvSeriesAPI
import com.example.movielister.model.TvSeriesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object TvSeriesNetwork {
    private fun createTvSeriesAPI(): TvSeriesAPI {
        return WebService.createRetrofit().create(TvSeriesAPI::class.java)
    }

    fun fetchPopularTvSeries(): List<TvSeriesModel> {
        val call = createTvSeriesAPI().fetchPopularTvSeries()
        var popularTvSeries = listOf<TvSeriesModel>()

        call.enqueue(
            object : Callback<List<TvSeriesModel>> {
                override fun onFailure(call: Call<List<TvSeriesModel>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<List<TvSeriesModel>>, response: Response<List<TvSeriesModel>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            popularTvSeries = it
                        }
                    }
                }
            })
        return popularTvSeries
    }

    fun fetchTvSerie(id: Int): TvSeriesModel? {
        val call = createTvSeriesAPI().fetchTvSerie(id)
        var tvSerie: TvSeriesModel? = null

        call.enqueue(
            object : Callback<TvSeriesModel> {
                override fun onFailure(call: Call<TvSeriesModel>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<TvSeriesModel>, response: Response<TvSeriesModel>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            tvSerie = it
                        }
                    }
                }
            })
        return tvSerie
    }
}