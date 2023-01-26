package com.example.movielister.login

object LoginInfo {
    var username: String? = null

    var loginStatus = LoginStatus.NOT_LOGGED_IN

    var sessionId: String? = null

    enum class LoginStatus {
        LOGGED_IN, NOT_LOGGED_IN
    }
}