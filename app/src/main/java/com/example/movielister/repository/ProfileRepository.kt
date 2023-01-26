package com.example.movielister.repository

import androidx.lifecycle.MutableLiveData
import com.example.movielister.api.ProfileNetwork
import com.example.movielister.model.AccountModel

class ProfileRepository {
    val currentUser: MutableLiveData<AccountModel> by lazy {
        MutableLiveData<AccountModel>()
    }

    suspend fun fetchAccountInfo(sessionId: String) {
        ProfileNetwork.profileService.fetchAccountInfo(sessionId)?.let {
            currentUser.value = it
        }
    }
}