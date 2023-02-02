package com.example.movielister.movies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movielister.data.MovieGenreHelper
import com.example.movielister.databinding.ActivityMovieDetailsBinding
import com.example.movielister.model.MovieModel
import com.example.movielister.model.MultiSearchModel
import com.example.movielister.person.PersonDetailsActivity
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
        var movie = intent.getSerializableExtra("movie") as MovieModel?
        if (movie == null) {
            val movieSearchModel = intent.getSerializableExtra("movieSearchModel") as MultiSearchModel.MovieSearchModel?
            movie = (movieSearchModel as MultiSearchModel.MovieSearchModel).toMovieModel()
        }
        movie?.let {
            bindMovieDetails(it)
            movieDetailsViewModel.fetchCredits(it.id!!)
            movieDetailsViewModel.fetchMovie(it.id)
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
                    binding.directorText.setOnClickListener {
                        val intent = Intent(this@MovieDetailsActivity, PersonDetailsActivity::class.java)
                        intent.putExtra("person_id", director.id)
                        startActivity(intent)
                    }
                }
                writer?.let {
                    binding.writersText.text = it.name
                    binding.writersText.setOnClickListener {
                        val intent = Intent(this@MovieDetailsActivity, PersonDetailsActivity::class.java)
                        intent.putExtra("person_id", writer.id)
                        startActivity(intent)
                    }
                }
                firstStar?.let {
                    binding.firstStarText.text = firstStar.name
                    binding.firstStarText.setOnClickListener {
                        val intent = Intent(this@MovieDetailsActivity, PersonDetailsActivity::class.java)
                        intent.putExtra("person_id", firstStar.id)
                        startActivity(intent)
                    }
                }
                secondStar?.let {
                    binding.secondStarText.text = secondStar.name
                    binding.secondStarText.setOnClickListener {
                        val intent = Intent(this@MovieDetailsActivity, PersonDetailsActivity::class.java)
                        intent.putExtra("person_id", secondStar.id)
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun MultiSearchModel.MovieSearchModel.toMovieModel(): MovieModel {
        return MovieModel(
            id = id, vote_average = vote_average,
            release_date = release_date, overview = overview,
            title = title, poster_path = poster_path, genre_ids = genre_ids,
            backdrop_path = null
        )
    }
}