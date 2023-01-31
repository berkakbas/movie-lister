package com.example.movielister.repository

import com.example.movielister.api.SearchNetwork
import com.example.movielister.model.MultiSearchModel
import com.example.movielister.model.TvSeriesModel
import kotlinx.coroutines.flow.MutableSharedFlow

class SearchRepository {
    val _searchResults = MutableSharedFlow<List<MultiSearchModel>>()

    val _seriesResults = MutableSharedFlow<List<TvSeriesModel>>()

    suspend fun searchMulti(query: String) {
        SearchNetwork.searchService.searchMulti(query)?.let {
            _searchResults.emit(it.results)
        }
    }

    suspend fun searchSeries(query: String) {
        SearchNetwork.searchService.searchTvSerie(query)?.let {
            val series = it
            _seriesResults.emit(series.results)
        }
    }
}