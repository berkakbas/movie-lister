package com.example.movielister.tvseries

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movielister.data.TvSeriesGenreHelper
import com.example.movielister.databinding.ActivityTvSeriesDetailsBinding
import com.example.movielister.helper.HelperFunctions
import com.example.movielister.model.MultiSearchModel
import com.example.movielister.model.TvSeriesModel
import com.example.movielister.person.PersonDetailsActivity
import com.example.movielister.util.invisible
import com.squareup.picasso.Picasso

class TvSeriesDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTvSeriesDetailsBinding

    private val seriesDetailsViewModel by lazy { ViewModelProvider(this)[TvSeriesDetailsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTvSeriesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        var series = intent.getSerializableExtra("series") as TvSeriesModel?

        if (series == null) {
            val seriesSearchModel = intent.getSerializableExtra("seriesSearchModel") as MultiSearchModel.SeriesSearchModel
            series = seriesSearchModel.ToTvSeriesModel()
        }
        bindSeriesDetails(series)
        bindCreators()
        series.id?.let {
            seriesDetailsViewModel.fetchCredits(it)
        }
    }

    private fun bindSeriesDetails(series: TvSeriesModel) {
        Picasso.get().load(series.imageUrl + series.posterPath).into(binding.seriesImage)
        binding.seriesRatingText.text = series.voteAverage.toString()
        binding.seriesTitle.text = series.name
        binding.seriesDescriptionText.text = series.overview
        series.firstAirDate?.let {
            binding.seriesDateText.text = HelperFunctions.organizeDate(it)
        }
        binding.seriesGenreText.text = TvSeriesGenreHelper.genreIdsToString(series.genreIds)
    }

    private fun bindCreators() {
        lifecycleScope.launchWhenStarted {
            seriesDetailsViewModel.currentCredits.collect { credits ->
                val creators = credits.crew.filter { it.job == "Executive Producer" }
                when {
                    creators.size == 0 -> binding.creatorTitle.invisible()
                    creators.size == 1 -> {
                        binding.firstCreatorText.text = creators[0].name
                        binding.firstCreatorText.setOnClickListener {
                            val personIntent = Intent(this@TvSeriesDetailsActivity, PersonDetailsActivity::class.java)
                            personIntent.putExtra("person_id", creators[0].id)
                            startActivity(personIntent)
                        }
                    }
                    creators.size >= 2 -> {
                        binding.firstCreatorText.text = creators[0].name
                        binding.secondCreatorText.text = creators[1].name

                        binding.firstCreatorText.setOnClickListener {
                            val personIntent = Intent(this@TvSeriesDetailsActivity, PersonDetailsActivity::class.java)
                            personIntent.putExtra("person_id", creators[0].id)
                            startActivity(personIntent)
                        }
                        binding.secondCreatorText.setOnClickListener {
                            val personIntent = Intent(this@TvSeriesDetailsActivity, PersonDetailsActivity::class.java)
                            personIntent.putExtra("person_id", creators[1].id)
                            startActivity(personIntent)
                        }
                    }
                }
            }
        }
    }

    private fun MultiSearchModel.SeriesSearchModel.ToTvSeriesModel(): TvSeriesModel {
        return TvSeriesModel(
            id = this.id, name = this.name,
            overview = this.overview!!, voteAverage = this.vote_average,
            posterPath = this.poster_path, firstAirDate = this.first_air_date,
            genreIds = this.genre_ids
        )
    }
}