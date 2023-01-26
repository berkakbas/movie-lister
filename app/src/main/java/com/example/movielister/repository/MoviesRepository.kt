package com.example.movielister.repository

import android.util.Log
import com.example.movielister.api.MoviesNetwork
import com.example.movielister.model.MovieCreditsModel
import com.example.movielister.model.MovieModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class MoviesRepository @Inject constructor() {
    val _popularMoviesList = MutableSharedFlow<List<MovieModel>>()

    suspend fun fetchPopularMovies() {
        MoviesNetwork.movieService.fetchPopularMovies()?.let {
            Log.d("xxx 3", "fetch")
            val popularMovies = it.results
            _popularMoviesList.emit(popularMovies)
        }
    }
}