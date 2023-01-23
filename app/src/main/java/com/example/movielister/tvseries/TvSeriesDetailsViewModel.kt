package com.example.movielister.tvseries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.repository.TvSeriesRepository
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class TvSeriesDetailsViewModel : ViewModel() {
    private val seriesRepository = TvSeriesRepository()

    val currentCredits = seriesRepository._currentCredits.asSharedFlow()

    fun fetchCredits(seriesID: Int) {
        viewModelScope.launch {
            runCatching {
                seriesRepository.fetchCredits(seriesID)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}