package com.example.movielister.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.model.MovieModel
import com.example.movielister.network.MoviesNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class MoviesViewModel() : ViewModel() {
    private val moviesAPI= MoviesNetwork.createMoviesAPI()

    private val _popularMoviesList = MutableSharedFlow<List<MovieModel>>()
    val popularMovieList = _popularMoviesList.asSharedFlow()

    private val _currentMovie = MutableSharedFlow<MovieModel>()
    val currentMovie = _currentMovie.asSharedFlow()

    fun fetchPopularMovies() {
        viewModelScope.launch {
            moviesAPI.fetchPopularMovies()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect {
                    _popularMoviesList.emit(it)
                }
        }
    }

    fun fetchMovie(movieID: Int) {
        viewModelScope.launch {
            moviesAPI.fetchMovie(movieID)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect {
                    _currentMovie.emit(it)
                }
        }
    }

}