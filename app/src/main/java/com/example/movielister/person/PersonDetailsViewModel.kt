package com.example.movielister.person

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.repository.PersonDetailsRepository
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class PersonDetailsViewModel : ViewModel() {

    private val personDetailsRepository = PersonDetailsRepository()

    val currentPerson = personDetailsRepository._currentPerson.asSharedFlow()

    val currentImage = personDetailsRepository._currentImage.asSharedFlow()

    fun fetchPerson(personID: Int) {
        viewModelScope.launch {
            runCatching {
                personDetailsRepository.fetchPerson(personID)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    fun fetchPersonImage(personID: Int) {
        viewModelScope.launch {
            runCatching {
                personDetailsRepository.fetchPersonImage(personID)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

}