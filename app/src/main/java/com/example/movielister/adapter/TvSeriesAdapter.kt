package com.example.movielister.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielister.databinding.SeriesRowLayoutBinding
import com.example.movielister.model.TvSeriesModel
import com.squareup.picasso.Picasso

class TvSeriesAdapter(private val seriesList: List<TvSeriesModel>) : RecyclerView.Adapter<TvSeriesAdapter.TvSeriesHolder>() {
    val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"

    class TvSeriesHolder(val binding: SeriesRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesHolder {
        val binding = SeriesRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvSeriesHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesHolder, position: Int) {
        val series = seriesList.get(position)
        holder.binding.seriesName.text = series.name
        holder.binding.ratingText.text = series.voteAverage.toString()
        Picasso.get().load(IMAGE_BASE_URL + series.posterPath).into(holder.binding.seriesImage)
        holder.itemView.setOnClickListener {
            /*
            val intent = Intent(holder.itemView.context,TvSeriesDetailsActivity::class.java)
            intent.putExtra("series_id", seriesList.get(position).id)
            holder.itemView.context.startActivity(intent)
             */
        }
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }
}