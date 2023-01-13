package com.example.movielister.repository

import androidx.lifecycle.MutableLiveData
import com.example.movielister.api.MoviesNetwork
import com.example.movielister.model.MovieModel
import kotlinx.coroutines.flow.MutableSharedFlow

class MoviesRepository {
    //val _popularMoviesList = MutableSharedFlow<List<MovieModel>>()

    val popularMoviesListLiveData = MutableLiveData<List<MovieModel>>()

    val _currentMovie = MutableSharedFlow<MovieModel>()

    suspend fun fetchPopularMovies() {
        MoviesNetwork.movieService.fetchPopularMovies()?.let {
            popularMoviesListLiveData.value = it.results
            //val popularMovies = it.results
            //_popularMoviesList.emit(popularMovies)
        }
    }

    suspend fun fetchMovie(movieId: Int) {
        MoviesNetwork.movieService.fetchMovie(movieId)?.let {
            val movie = it
            _currentMovie.emit(movie)
        }
    }
}