package com.example.movielister.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.movielister.R
import com.example.movielister.databinding.ActivityMainBinding
import com.example.movielister.factory.BasicFragmentFactory
import com.example.movielister.movies.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = MoviesFragment()
        setCurrentFragment(homeFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            setCurrentFragment(BasicFragmentFactory.generateFragment(it.itemId))
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }
}