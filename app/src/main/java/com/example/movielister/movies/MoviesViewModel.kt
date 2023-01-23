package com.example.movielister.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movielister.repository.MoviesRepository
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesViewModel
@Inject
constructor(private val moviesRepository: MoviesRepository) : ViewModel() {

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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val moviesRepository = MoviesRepository()
                MoviesViewModel(moviesRepository = moviesRepository)
            }
        }
    }

}