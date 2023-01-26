package com.example.movielister.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielister.data.MovieGenreHelper
import com.example.movielister.databinding.MovieRowLayoutBinding
import com.example.movielister.model.MovieModel
import com.example.movielister.movies.MovieDetailsActivity
import com.example.movielister.util.organizeDate
import com.squareup.picasso.Picasso

class MovieListAdapter(private val movieList: List<MovieModel>) : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {
    class MovieHolder(val binding: MovieRowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding = MovieRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movieList.get(position)
        holder.binding.movieName.text = movie.title
        movie.genre_ids?.let {
            holder.binding.genreText.text = MovieGenreHelper.genreIdsToString(it)
        }
        holder.binding.releaseDateText.text = movie.release_date?.organizeDate()
        holder.binding.ratingText.text = movie.vote_average?.toString()
        Picasso.get().load(movie.imageUrl + movie.poster_path).into(holder.binding.movieImage)
        Log.d("xxx 4 adapter", movie.imageUrl + movie.poster_path)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailsActivity::class.java)
            intent.putExtra("movie", movieList.get(position))
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}