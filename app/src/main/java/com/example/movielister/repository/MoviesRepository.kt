package com.example.movielister.repository

import com.example.movielister.api.MoviesNetwork
import com.example.movielister.model.MovieCreditsModel
import com.example.movielister.model.MovieModel
import kotlinx.coroutines.flow.MutableSharedFlow

class MoviesRepository {
    val _popularMoviesList = MutableSharedFlow<List<MovieModel>>()

    val _currentMovie = MutableSharedFlow<MovieModel>()

    val _currentCredits = MutableSharedFlow<MovieCreditsModel>()

    suspend fun fetchPopularMovies() {
        MoviesNetwork.movieService.fetchPopularMovies()?.let {
            val popularMovies = it.results
            _popularMoviesList.emit(popularMovies)
        }
    }

    suspend fun fetchMovie(movieId: Int) {
        MoviesNetwork.movieService.fetchMovie(movieId)?.let {
            val movie = it
            _currentMovie.emit(movie)
        }
    }

    suspend fun fetchCredits(movieId: Int) {
        MoviesNetwork.movieService.fetchCredits(movieId)?.let {
            val credits = it
            _currentCredits.emit(credits)
        }
    }
}