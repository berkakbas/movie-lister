package com.example.movielister.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movielister.model.SessionModel
import com.example.movielister.model.TokenModel
import com.example.movielister.network.LoginNetwork
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginService = LoginNetwork.createLoginAPI()

    private val _token = MutableSharedFlow<TokenModel>()
    val token = _token.asSharedFlow()

    private val _sessionModel = MutableSharedFlow<SessionModel>()
    val sessionModel = _sessionModel.asSharedFlow()

    private val _loginToken = MutableSharedFlow<TokenModel>()
    val loginToken = _loginToken.asSharedFlow()

    fun createToken() {
        viewModelScope.launch {
            try {
                val token = loginService.createToken()
                _token.emit(token)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createSession(token: String) {
        viewModelScope.launch {
            try {
                val sessionModel = loginService.createSession(token)
                _sessionModel.emit(sessionModel)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun createSessionWithLogin(username: String, password: String, token: String) {
        viewModelScope.launch {
            try {
                val loginToken = loginService.createSessionWithLogin(username, password, token)
                _loginToken.emit(loginToken)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}