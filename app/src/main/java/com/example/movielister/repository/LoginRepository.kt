package com.example.movielister.repository

import com.example.movielister.api.LoginNetwork
import com.example.movielister.model.SessionModel
import com.example.movielister.model.TokenModel
import kotlinx.coroutines.flow.MutableSharedFlow

class LoginRepository {
    val _token = MutableSharedFlow<TokenModel>()

    val _sessionModel = MutableSharedFlow<SessionModel>()

    val _loginToken = MutableSharedFlow<TokenModel>()

    suspend fun createToken() {
        LoginNetwork.loginService.createToken()?.let {
            val token = it
            _token.emit(token)
        }
    }

    suspend fun createSession(token: String) {
        LoginNetwork.loginService.createSession(token)?.let {
            val sessionModel = it
            _sessionModel.emit(sessionModel)
        }
    }

    suspend fun createSessionWithLogin(username: String, password: String, token: String) {
        LoginNetwork.loginService.createSessionWithLogin(username, password, token)?.let {
            val loginToken = it
            _loginToken.emit(loginToken)
        }
    }
}