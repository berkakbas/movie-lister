package com.example.movielister.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movielister.data.MovieGenreHelper
import com.example.movielister.databinding.ActivityMovieDetailsBinding
import com.example.movielister.helper.HelperFunctions
import com.example.movielister.model.MovieModel
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val movie = intent.getSerializableExtra("movie") as MovieModel

        Picasso.get().load(movie.imageUrl + movie.posterPath).into(binding.movieImage)
        binding.ratingText.text = movie.voteAverage.toString()
        binding.movieTitle.text = movie.title
        binding.genreText.text = MovieGenreHelper.genreIdsToString(movie.genreIds)
        binding.dateText.text = HelperFunctions.organizeDate(movie.releaseDate)
        binding.descriptionText.text = movie.overview
    }
}