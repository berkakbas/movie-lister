package com.example.movielister.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielister.databinding.SeriesHolderBinding
import com.example.movielister.model.TvSeriesModel
import com.example.movielister.tvseries.TvSeriesDetailsActivity
import com.squareup.picasso.Picasso

class SeriesViewPagerAdapter(private val seriesList: List<TvSeriesModel>) : RecyclerView.Adapter<SeriesViewPagerAdapter.ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding = SeriesHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind(seriesList[position])
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    class ViewPagerHolder(private var seriesHolderBinding: SeriesHolderBinding) : RecyclerView.ViewHolder(seriesHolderBinding.root) {
        fun bind(series: TvSeriesModel) {
            seriesHolderBinding.seriesImage.setOnClickListener {
                val intent = Intent(seriesHolderBinding.root.context, TvSeriesDetailsActivity::class.java)
                intent.putExtra("series", series)
                seriesHolderBinding.root.context.startActivity(intent)
            }

            seriesHolderBinding.seriesName.text = series.name
            seriesHolderBinding.ratingText.text = series.voteAverage.toString()
            Picasso.get().load(series.imageUrl + series.posterPath).into(seriesHolderBinding.seriesImage)
        }
    }
}