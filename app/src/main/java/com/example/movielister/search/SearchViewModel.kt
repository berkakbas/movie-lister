package com.example.movielister.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.repository.SearchRepository
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val searchRepository = SearchRepository()

    val searchResults = searchRepository._searchResults.asSharedFlow()

    val seriesResults = searchRepository._seriesResults.asSharedFlow()

    fun searchMulti(query: String) {
        viewModelScope.launch {
            try {
                searchRepository.searchMulti(query)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchTvSeries(query: String) {
        viewModelScope.launch {
            try {
                searchRepository.searchSeries(query)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}