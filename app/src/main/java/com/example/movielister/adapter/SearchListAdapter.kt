package com.example.movielister.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielister.R
import com.example.movielister.databinding.SearchResultRowBinding
import com.example.movielister.model.MultiSearchModel
import com.example.movielister.movies.MovieDetailsActivity
import com.example.movielister.tvseries.TvSeriesDetailsActivity
import com.squareup.picasso.Picasso

class SearchListAdapter(private val searchList: List<MultiSearchModel>, private val context: Context) : RecyclerView.Adapter<SearchListAdapter.SearchHolder>() {
    class SearchHolder(val binding: SearchResultRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val binding = SearchResultRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val searchItem = searchList.get(position)
        when (searchItem) {
            is MultiSearchModel.MovieSearchModel -> {
                holder.binding.searchName.text = searchItem.title
                Picasso.get().load(searchItem.imageUrl + searchItem.poster_path).into(holder.binding.searchImage)
                holder.binding.searchTypeImage.setImageResource(R.drawable.ic_movie_blue)
                holder.binding.searchTypeText.text = context.getText(R.string.movie)
                holder.binding.searchItemView.setOnClickListener {
                    val intent = Intent(context, MovieDetailsActivity::class.java)
                    intent.putExtra("movieSearchModel", searchItem)
                    context.startActivity(intent)
                }
            }
            is MultiSearchModel.SeriesSearchModel -> {
                holder.binding.searchName.text = searchItem.name
                Picasso.get().load(searchItem.imageUrl + searchItem.poster_path).into(holder.binding.searchImage)
                holder.binding.searchTypeImage.setImageResource(R.drawable.ic_tv_blue)
                holder.binding.searchTypeText.text = context.getText(R.string.tv_series)
                holder.binding.searchItemView.setOnClickListener {
                    val intent = Intent(context, TvSeriesDetailsActivity::class.java)
                    intent.putExtra("seriesSearchModel", searchItem)
                    context.startActivity(intent)
                }
            }
            is MultiSearchModel.PersonSearchModel -> {
                holder.binding.searchName.text = searchItem.name
                holder.binding.searchTypeImage.setImageResource(R.drawable.ic_person_blue)
                holder.binding.searchTypeText.text = context.getText(R.string.person)
            }
        }
    }

    override fun getItemCount(): Int {
        return searchList.size
    }
}