package com.example.movielister.repository

import com.example.movielister.api.TvSeriesNetwork
import com.example.movielister.model.TvSeriesModel
import kotlinx.coroutines.flow.MutableSharedFlow

class TvSeriesRepository {
    val _popularSeriesList = MutableSharedFlow<List<TvSeriesModel>>()

    val _currentSerie = MutableSharedFlow<TvSeriesModel>()

    suspend fun fetchPopularTvSeries() {
        TvSeriesNetwork.seriesService.fetchPopularTvSeries()?.let {
            val popularSeries = it.results
            _popularSeriesList.emit(popularSeries)
        }
    }

    suspend fun fetchTvSerie(seriesId: Int) {
        TvSeriesNetwork.seriesService.fetchTvSerie(seriesId)?.let {
            val series = it
            _currentSerie.emit(series)
        }
    }
}