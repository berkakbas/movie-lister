package com.example.movielister.repository

import com.example.movielister.api.MoviesNetwork
import com.example.movielister.model.MovieCreditsModel
import com.example.movielister.model.MovieDetailsModel
import kotlinx.coroutines.flow.MutableSharedFlow

class MovieDetailsRepository {
    val _currentMovie = MutableSharedFlow<MovieDetailsModel>()

    val _currentCredits = MutableSharedFlow<MovieCreditsModel>()

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