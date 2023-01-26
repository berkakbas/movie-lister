package com.example.movielister.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.repository.MovieDetailsRepository
import com.example.movielister.repository.MoviesRepository
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {

    private val movieDetailsRepository = MovieDetailsRepository()

    val currentMovie = movieDetailsRepository._currentMovie.asSharedFlow()

    val currentCredits = movieDetailsRepository._currentCredits.asSharedFlow()

    fun fetchCredits(movieID: Int) {
        viewModelScope.launch {
            runCatching {
                movieDetailsRepository.fetchCredits(movieID)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun fetchMovie(movieID: Int) {
        viewModelScope.launch {
            runCatching {
                movieDetailsRepository.fetchMovie(movieID)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}