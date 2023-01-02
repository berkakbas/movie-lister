package com.example.movielister.tvseries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.repository.TvSeriesRepository
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class TvSeriesViewModel : ViewModel() {
    private val tvSeriesRepository = TvSeriesRepository()

    val popularSeriesList = tvSeriesRepository._popularSeriesList.asSharedFlow()

    val currentSerie = tvSeriesRepository._currentSerie.asSharedFlow()

    fun fetchPopularTvSeries() {
        viewModelScope.launch {
            runCatching {
                tvSeriesRepository.fetchPopularTvSeries()
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun fetchTvSerie(serieId: Int) {
        viewModelScope.launch {
            runCatching {
                tvSeriesRepository.fetchTvSerie(serieId)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }
}