package com.example.movielister.repository

import com.example.movielister.api.PersonNetwork
import com.example.movielister.model.PersonImageModel
import com.example.movielister.model.PersonModel
import kotlinx.coroutines.flow.MutableSharedFlow

class PersonDetailsRepository {
    val _currentPerson = MutableSharedFlow<PersonModel>()

    val _currentImage = MutableSharedFlow<PersonImageModel>()

    suspend fun fetchPerson(personId: Int) {
        PersonNetwork.personService.fetchPersonInfo(personId)?.let {
            _currentPerson.emit(it)
        }
    }

    suspend fun fetchPersonImage(personId: Int) {
        PersonNetwork.personService.fetchImages(personId)?.let {
            _currentImage.emit(it.profiles.first())
        }
    }
}