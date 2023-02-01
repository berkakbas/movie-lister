package com.example.movielister.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movielister.databinding.ActivityLoginBinding
import com.example.movielister.main.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        loginViewModel.createToken()
        observeLogin()
        observeSession()

        bindClickListeners()

        setContentView(binding.root)
    }

    private fun loginClicked() {
        val username = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        loginViewModel.token.value?.let {
            loginViewModel.createSessionWithLogin(username, password, it.request_token!!)
        }
    }

    private fun observeLogin() {
        lifecycleScope.launchWhenStarted {
            loginViewModel.loginToken.collect {
                if (it.success) {
                    Toast.makeText(this@LoginActivity, "Success", Toast.LENGTH_LONG).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    LoginInfo.loginStatus = LoginInfo.LoginStatus.LOGGED_IN
                    loginViewModel.createSession(it.request_token!!)
                } else {
                    Toast.makeText(this@LoginActivity, "Wrong username or password", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun observeSession() {
        lifecycleScope.launchWhenStarted {
            loginViewModel.sessionModel.collect {
                loginViewModel.sessionId.value = it.session_id
                LoginInfo.sessionId = it.session_id
            }
        }
    }

    private fun bindClickListeners() {
        binding.loginButton.setOnClickListener {
            loginClicked()
        }

        binding.forgotPasswordText.setOnClickListener {
            val forgotPasswordUri = Uri.parse("https://www.themoviedb.org/reset-password")
            val intent = Intent(Intent.ACTION_VIEW, forgotPasswordUri)
            startActivity(intent)
        }

        binding.registerText.setOnClickListener {
            val registerUri = Uri.parse("https://www.themoviedb.org/signup")
            val intent = Intent(Intent.ACTION_VIEW, registerUri)
            startActivity(intent)
        }
    }

}