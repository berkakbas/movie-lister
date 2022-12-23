package com.example.movielister.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.model.PersonModel
import com.example.movielister.network.PersonNetwork
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val personService = PersonNetwork.createPersonAPI()

    private val _currentUser = MutableSharedFlow<PersonModel>()
    val currentUser = _currentUser.asSharedFlow()

    fun fetchPersonInfo(userId: Int) {
        viewModelScope.launch {
            try {
                personService.fetchPersonInfo(userId)?.let {
                    val user = it
                    _currentUser.emit(user)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}