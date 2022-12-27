package com.example.movielister.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movielister.databinding.ActivityMainBinding
import com.example.movielister.databinding.ActivityMovieDetailsBinding

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}