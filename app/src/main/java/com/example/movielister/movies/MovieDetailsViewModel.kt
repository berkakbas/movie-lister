package com.example.movielister.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.repository.MoviesRepository
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel : ViewModel() {
    private val moviesRepository = MoviesRepository()

    val currentCredits = moviesRepository._currentCredits.asSharedFlow()

    fun fetchCredits(movieID: Int) {
        viewModelScope.launch {
            runCatching {
                moviesRepository.fetchCredits(movieID)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}