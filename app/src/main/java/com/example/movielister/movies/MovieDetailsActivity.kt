package com.example.movielister.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movielister.data.MovieGenreHelper
import com.example.movielister.databinding.ActivityMovieDetailsBinding
import com.example.movielister.helper.HelperFunctions
import com.example.movielister.model.MovieModel
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailsBinding

    private val movieDetailsViewModel by lazy { ViewModelProvider(this)[MovieDetailsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val movie = intent.getSerializableExtra("movie") as MovieModel

        movieDetailsViewModel.fetchCredits(movie.id)

        Picasso.get().load(movie.imageUrl + movie.posterPath).into(binding.movieImage)
        binding.ratingText.text = movie.voteAverage.toString()
        binding.movieTitle.text = movie.title
        binding.genreText.text = MovieGenreHelper.genreIdsToString(movie.genreIds)
        binding.dateText.text = HelperFunctions.organizeDate(movie.releaseDate)
        binding.descriptionText.text = movie.overview

        lifecycleScope.launchWhenStarted {
            movieDetailsViewModel.currentCredits.collect { credits ->
                val director = credits.crew.firstOrNull { it.job == "Director" }
                val writer = credits.crew.firstOrNull { it.department == "Writing"}
                val firstStar = credits.cast.firstOrNull { it.order == 0 }
                val secondStar = credits.cast.firstOrNull { it.order == 1 }

                director?.let {
                    binding.directorText.text = it.name
                }
                writer?.let {
                    binding.writersText.text = it.name
                }
                binding.starsText.text = firstStar?.name + ", " + secondStar?.name
            }
        }
    }
}