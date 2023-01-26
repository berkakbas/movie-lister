package com.example.movielister.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val profileRepository = ProfileRepository()

    var currentUser = profileRepository.currentUser

    fun fetchAccountInfo(sessionId: String) {
        viewModelScope.launch {
            try {
                profileRepository.fetchAccountInfo(sessionId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}