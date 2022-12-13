package com.example.movielister.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.movielister.R
import com.example.movielister.databinding.ActivityMainBinding
import com.example.movielister.movies.MoviesFragment
import com.example.movielister.profile.ProfileFragment
import com.example.movielister.search.SearchFragment
import com.example.movielister.tvseries.TvSeriesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val homeFragment = MoviesFragment()
        val moviesFragment = TvSeriesFragment()
        val searchFragment = SearchFragment()
        val profileFragment = ProfileFragment()

        setCurrentFragment(homeFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.movies -> setCurrentFragment(homeFragment)
                R.id.tvSeries -> setCurrentFragment(moviesFragment)
                R.id.search -> setCurrentFragment(searchFragment)
                R.id.profile -> setCurrentFragment(profileFragment)
            }
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