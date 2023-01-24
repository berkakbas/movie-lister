package com.example.movielister.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.model.PersonModel
import com.example.movielister.repository.ProfileRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val profileRepository = ProfileRepository()

    var currentUser = profileRepository.currentUser

    val isLoggedIn = MutableSharedFlow<Boolean>()

    fun fetchPersonInfo(userId: Int) {
        viewModelScope.launch {
            try {
                profileRepository.fetchPersonInfo(userId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}