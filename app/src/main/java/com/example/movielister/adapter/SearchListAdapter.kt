package com.example.movielister.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielister.R
import com.example.movielister.databinding.SearchResultRowBinding
import com.example.movielister.model.MovieSearchModel
import com.example.movielister.model.MultiSearchModel
import com.example.movielister.model.PersonSearchModel
import com.example.movielister.model.SeriesSearchModel
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
            is MovieSearchModel -> {
                holder.binding.searchName.text = searchItem.title
                Picasso.get().load(searchItem.imageUrl + searchItem.poster_path).into(holder.binding.searchImage)
                holder.binding.searchImage.setImageResource(R.drawable.ic_movie_blue)
                holder.binding.searchTypeText.text = context.getText(R.string.movie)
            }
            is SeriesSearchModel -> {
                holder.binding.searchName.text = searchItem.name
                Picasso.get().load(searchItem.imageUrl + searchItem.poster_path).into(holder.binding.searchImage)
                holder.binding.searchImage.setImageResource(R.drawable.ic_clock_blue)
                holder.binding.searchTypeText.text = context.getText(R.string.tv_series)
            }
            is PersonSearchModel -> {
                holder.binding.searchName.text = searchItem.name
                holder.binding.searchImage.setImageResource(R.drawable.ic_profile)
                holder.binding.searchTypeText.text = "Actor"
            }
        }
    }

    override fun getItemCount(): Int {
        return searchList.size
    }
}