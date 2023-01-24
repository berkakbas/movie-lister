package com.example.movielister.repository

import androidx.lifecycle.MutableLiveData
import com.example.movielister.api.ProfileNetwork
import com.example.movielister.model.PersonModel
import kotlinx.coroutines.flow.MutableSharedFlow

class ProfileRepository {
    val currentUser: MutableLiveData<PersonModel> by lazy {
        MutableLiveData<PersonModel>()
    }

    val _isLoggedIn = MutableSharedFlow<Boolean>()

    suspend fun fetchPersonInfo(id: Int) {
        ProfileNetwork.profileService.fetchPersonInfo(id)?.let {
            val user = it
            currentUser.value = user
        }
    }
}