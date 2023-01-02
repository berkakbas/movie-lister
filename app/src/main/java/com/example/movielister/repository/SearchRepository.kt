package com.example.movielister.repository

import com.example.movielister.api.SearchNetwork
import com.example.movielister.model.MovieModel
import com.example.movielister.model.TvSeriesModel
import kotlinx.coroutines.flow.MutableSharedFlow

class SearchRepository {
    val _movieResults = MutableSharedFlow<List<MovieModel>>()

    val _seriesResults = MutableSharedFlow<List<TvSeriesModel>>()

    suspend fun searchMovie(query: String) {
        SearchNetwork.searchService.searchMovie(query)?.let {
            val movies = it
            _movieResults.emit(movies.results)
        }
    }

    suspend fun searchSeries(query: String) {
        SearchNetwork.searchService.searchTvSerie(query)?.let {
            val series = it
            _seriesResults.emit(series.results)
        }
    }
}