package com.example.movielister.repository

import com.example.movielister.api.MoviesNetwork
import com.example.movielister.model.MovieModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

class MoviesRepository @Inject constructor() {
    val _popularMoviesList = MutableSharedFlow<List<MovieModel>>()

    suspend fun fetchPopularMovies() {
        MoviesNetwork.movieService.fetchPopularMovies()?.let {
            val popularMovies = it.results
            _popularMoviesList.emit(popularMovies)
        }
    }
}