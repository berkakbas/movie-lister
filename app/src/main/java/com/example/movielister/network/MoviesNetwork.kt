package com.example.movielister.network

import com.example.movielister.api.MoviesAPI
import com.example.movielister.model.MovieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MoviesNetwork {
    private fun createMoviesAPI(): MoviesAPI {
        return WebService.createRetrofit().create(MoviesAPI::class.java)
    }

    fun fetchPopularMovies(): List<MovieModel> {
        val call = createMoviesAPI().fetchPopularMovies()
        var popularMovies = listOf<MovieModel>()

        call.enqueue(
            object : Callback<List<MovieModel>> {
                override fun onFailure(call: Call<List<MovieModel>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<List<MovieModel>>, response: Response<List<MovieModel>>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            popularMovies = it
                        }
                    }
                }
            })
        return popularMovies
    }

    fun fetchMovie(id: Int): MovieModel {
        val call = createMoviesAPI().fetchMovie(id)
        lateinit var movie: MovieModel

        call.enqueue(
            object : Callback<MovieModel> {
                override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            movie = it
                        }
                    }
                }
            })
        return movie
    }
}