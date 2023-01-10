package com.example.movielister.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.repository.MoviesRepository
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MoviesViewModel() : ViewModel() {
    private val moviesRepository = MoviesRepository()

    val popularMoviesList = moviesRepository._popularMoviesList.asSharedFlow()

    val currentMovie = moviesRepository._currentMovie.asSharedFlow()

    fun fetchPopularMovies() {
        viewModelScope.launch {
            runCatching {
                moviesRepository.fetchPopularMovies()
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun fetchMovie(movieID: Int) {
        viewModelScope.launch {
            runCatching {
                moviesRepository.fetchMovie(movieID)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}