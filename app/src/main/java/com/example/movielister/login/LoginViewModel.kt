package com.example.movielister.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.repository.LoginRepository
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository()

    val token = loginRepository._token.asSharedFlow()

    val sessionModel = loginRepository._sessionModel.asSharedFlow()

    val loginToken = loginRepository._loginToken.asSharedFlow()

    fun createToken() {
        viewModelScope.launch {
            try {
                loginRepository.createToken()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createSession(token: String) {
        viewModelScope.launch {
            try {
                loginRepository.createSession(token)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createSessionWithLogin(username: String, password: String, token: String) {
        viewModelScope.launch {
            try {
                loginRepository.createSessionWithLogin(username, password, token)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}