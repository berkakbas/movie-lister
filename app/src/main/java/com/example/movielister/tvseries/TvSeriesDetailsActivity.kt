package com.example.movielister.tvseries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movielister.data.TvSeriesGenreHelper
import com.example.movielister.databinding.ActivityTvSeriesDetailsBinding
import com.example.movielister.helper.HelperFunctions
import com.example.movielister.model.TvSeriesModel
import com.squareup.picasso.Picasso

class TvSeriesDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvSeriesDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTvSeriesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val series = intent.getSerializableExtra("series") as TvSeriesModel

        Picasso.get().load(series.imageUrl + series.posterPath).into(binding.seriesImage)
        binding.seriesRatingText.text = series.voteAverage.toString()
        binding.seriesTitle.text = series.name
        binding.seriesDescriptionText.text = series.overview
        binding.seriesDateText.text = HelperFunctions.organizeDate(series.firstAirDate)
        binding.seriesGenreText.text = TvSeriesGenreHelper.genreIdsToString(series.genreIds)
    }
}