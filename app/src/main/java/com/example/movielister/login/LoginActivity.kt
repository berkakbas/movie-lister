package com.example.movielister.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movielister.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val loginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        loginViewModel.createToken()

        lifecycleScope.launchWhenStarted {
            loginViewModel.loginToken.collect {
                if (it.success) {
                    Toast.makeText(this@LoginActivity, "Success", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@LoginActivity, "Fail", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.loginButton.setOnClickListener {
            loginClicked()
        }
        setContentView(binding.root)
    }

    private fun loginClicked() {
        val username = binding.usernameEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        loginViewModel.token.value?.let {
            loginViewModel.createSessionWithLogin(username, password, it.request_token)
        }
    }

}