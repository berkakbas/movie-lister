package com.example.movielister.movies

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movielister.database.MovieDatabase
import com.example.movielister.database.MovieSharedPreferences
import com.example.movielister.model.MovieModel
import com.example.movielister.repository.MoviesRepository
import com.example.movielister.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MoviesViewModel(application: Application) : BaseViewModel(application) {
    private val moviesRepository = MoviesRepository()

    val popularMoviesList = moviesRepository.popularMoviesListLiveData

    val currentMovie = moviesRepository._currentMovie.asSharedFlow()

    private var movieSharedPreferences = MovieSharedPreferences(getApplication())
    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L //5 minutes

    val moviesLoading = MutableLiveData<Boolean>()

    fun fetchPopularMovies() {
        viewModelScope.launch {
            runCatching {
                moviesRepository.fetchPopularMovies()
                Toast.makeText(getApplication(), "got data from API", Toast.LENGTH_LONG).show()
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

    private fun storeInSQLite(movieList: List<MovieModel>) {
        launch {
            Toast.makeText(getApplication(), "stored in SQLite", Toast.LENGTH_LONG).show()
            val dao = MovieDatabase(getApplication()).movieDao()
            dao.deleteAllMovies()
            val listLong = dao.insertAll(*movieList.toTypedArray())
            var i = 0
            while (i < movieList.size) {
                movieList[i].uuid = listLong[i].toInt()
                i = i + 1
            }
            showMovies(movieList)
        }
        movieSharedPreferences.saveTime(System.nanoTime())
    }

    fun refreshData() {
        val updateTime = movieSharedPreferences.getTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime <
            refreshTime
        ) {
            getDataFromSQLite()
        } else {
            fetchPopularMovies()
        }
    }

    private fun getDataFromSQLite() {
        Toast.makeText(getApplication(), "got data from SQLite", Toast.LENGTH_LONG).show()
        moviesLoading.value = true
        launch {
            val movies = MovieDatabase(getApplication())
                .movieDao()
                .getAllMovies()
            showMovies(movies)
        }
    }

    private fun showMovies(movieList: List<MovieModel>) {
        popularMoviesList.value = movieList
        moviesLoading.value = false
    }
}


