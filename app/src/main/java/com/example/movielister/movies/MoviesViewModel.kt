package com.example.movielister.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.model.MovieModel
import com.example.movielister.network.MoviesNetwork
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MoviesViewModel() : ViewModel() {
    private val moviesService = MoviesNetwork.createMoviesAPI()

    private val _popularMoviesList = MutableSharedFlow<List<MovieModel>>()
    val popularMoviesList = _popularMoviesList.asSharedFlow()

    private val _currentMovie = MutableSharedFlow<MovieModel>()
    val currentMovie = _currentMovie.asSharedFlow()

    fun fetchPopularMovies() {
        viewModelScope.launch {
            runCatching {
                moviesService.fetchPopularMovies()?.let {
                    val popularMovies = it.results
                    _popularMoviesList.emit(popularMovies)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun fetchMovie(movieID: Int) {
        viewModelScope.launch {
            runCatching {
                moviesService.fetchMovie(movieID)?.let {
                    val movie = it
                    _currentMovie.emit(movie)
                }
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}