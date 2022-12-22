package com.example.movielister.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.model.MovieModel
import com.example.movielister.model.TvSeriesModel
import com.example.movielister.network.SearchNetwork
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val searchService = SearchNetwork.createSearchAPI()

    private val _movieResults = MutableSharedFlow<List<MovieModel>>()
    val movieResults = _movieResults.asSharedFlow()

    private val _seriesResults = MutableSharedFlow<List<TvSeriesModel>>()
    val seriesResults = _seriesResults.asSharedFlow()

    fun searchMovie(query: String) {
        viewModelScope.launch {
            try {
                val movies = searchService.searchMovie(query)
                _movieResults.emit(movies.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchTvSeries(query: String) {
        viewModelScope.launch {
            try {
                val series = searchService.searchTvSerie(query)
                _seriesResults.emit(series.results)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}