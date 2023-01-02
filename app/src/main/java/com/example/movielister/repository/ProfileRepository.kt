package com.example.movielister.repository

import com.example.movielister.api.ProfileNetwork
import com.example.movielister.model.PersonModel
import kotlinx.coroutines.flow.MutableSharedFlow

class ProfileRepository {
    val _currentUser = MutableSharedFlow<PersonModel>()

    suspend fun fetchPersonInfo(id: Int) {
        ProfileNetwork.profileService.fetchPersonInfo(id)?.let {
            val user = it
            _currentUser.emit(user)
        }
    }
}