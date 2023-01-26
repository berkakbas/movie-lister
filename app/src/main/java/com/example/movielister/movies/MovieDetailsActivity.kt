package com.example.movielister.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movielister.data.MovieGenreHelper
import com.example.movielister.databinding.ActivityMovieDetailsBinding
import com.example.movielister.model.MovieModel
import com.example.movielister.util.organizeDate
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
        bindMovieDetails(movie)

        movie.id?.let {
            movieDetailsViewModel.fetchCredits(it)
            movieDetailsViewModel.fetchMovie(it)
        }
        bindCredits()
        bindOtherDetails()
    }

    private fun bindMovieDetails(movie: MovieModel) {
        Picasso.get().load(movie.imageUrl + movie.poster_path).into(binding.movieImage)
        binding.ratingText.text = movie.vote_average.toString()
        binding.movieTitle.text = movie.title
        movie.genre_ids?.let {
            binding.genreText.text = MovieGenreHelper.genreIdsToString(it)
        }
        binding.dateText.text = movie.release_date?.organizeDate()
        binding.descriptionText.text = movie.overview
    }

    private fun bindOtherDetails() {
        lifecycleScope.launchWhenStarted {
            movieDetailsViewModel.currentMovie.collect { movieDetails ->
                binding.durationText.text = movieDetails.runtime.toString()
            }
        }
    }

    private fun bindCredits() {
        lifecycleScope.launchWhenStarted {
            movieDetailsViewModel.currentCredits.collect { credits ->
                val director = credits.crew.firstOrNull { it.job == "Director" }
                val writer = credits.crew.firstOrNull { it.department == "Writing" }
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