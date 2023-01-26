package com.example.movielister.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.movielister.databinding.FragmentProfileBinding
import com.example.movielister.login.LoginActivity
import com.example.movielister.login.LoginInfo
import com.example.movielister.util.hide
import com.example.movielister.util.show

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val profileViewModel by lazy { ViewModelProvider(requireActivity())[ProfileViewModel::class.java] }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginButton.setOnClickListener {
            loadLoginFragment()
        }

        if (LoginInfo.loginStatus == LoginInfo.LoginStatus.LOGGED_IN) {
            binding.helloText.show()
            binding.usernameText.show()
            if (LoginInfo.username == null) {
                getUsername()
            } else {
                binding.usernameText.text = LoginInfo.username
            }
            binding.loginButton.hide()
        } else {
            binding.helloText.hide()
            binding.usernameText.hide()
            binding.loginButton.show()
        }

    }

    private fun loadLoginFragment() {
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
    }

    private fun getUsername() {
        LoginInfo.sessionId?.let {
            profileViewModel.fetchAccountInfo(it)
        }
        profileViewModel.currentUser.observe(viewLifecycleOwner) { user ->
            LoginInfo.username = user.username
            binding.usernameText.text = user.username
        }
    }
}