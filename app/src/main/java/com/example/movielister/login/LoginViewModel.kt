package com.example.movielister.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.model.TokenModel
import com.example.movielister.repository.LoginRepository
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginRepository()

    val token = loginRepository.token

    val sessionId = loginRepository.sessionId

    var sessionModel = loginRepository._sessionModel.asSharedFlow()

    var loginToken = loginRepository._loginToken.asSharedFlow()

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
                val failedToken = TokenModel(success = false, null, null)
                loginRepository._loginToken.emit(failedToken)
                e.printStackTrace()
            }
        }
    }
}