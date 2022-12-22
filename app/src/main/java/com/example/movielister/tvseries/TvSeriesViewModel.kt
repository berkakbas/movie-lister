package com.example.movielister.tvseries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.model.TvSeriesModel
import com.example.movielister.network.TvSeriesNetwork
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class TvSeriesViewModel : ViewModel() {
    private val tvSeriesService = TvSeriesNetwork.createTvSeriesAPI()

    private val _popularSeriesList = MutableSharedFlow<List<TvSeriesModel>>()
    val popularSeriesList = _popularSeriesList.asSharedFlow()

    private val _currentSerie = MutableSharedFlow<TvSeriesModel>()
    val currentSerie = _currentSerie.asSharedFlow()

    fun fetchPopularTvSeries() {
        viewModelScope.launch {
            try {
                val popularSeries = tvSeriesService.fetchPopularTvSeries().results
                _popularSeriesList.emit(popularSeries)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchTvSerie(serieId: Int) {
        viewModelScope.launch {
            try {
                val serie = tvSeriesService.fetchTvSerie(serieId)
                _currentSerie.emit(serie)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}