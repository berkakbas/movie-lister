package com.example.movielister.repository

import androidx.lifecycle.MutableLiveData
import com.example.movielister.api.LoginNetwork
import com.example.movielister.model.SessionModel
import com.example.movielister.model.TokenModel
import kotlinx.coroutines.flow.MutableSharedFlow

class LoginRepository {
    val token: MutableLiveData<TokenModel> by lazy {
        MutableLiveData<TokenModel>()
    }

    val sessionId: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    var _sessionModel = MutableSharedFlow<SessionModel>()

    var _loginToken = MutableSharedFlow<TokenModel>()

    suspend fun createToken() {
        LoginNetwork.loginService.createToken()?.let {
            token.value = it
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