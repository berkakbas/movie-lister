package com.example.movielister.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielister.databinding.MovieRowLayoutBinding
import com.example.movielister.model.MovieModel
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
        Picasso.get().load(movie.posterPath).resize(100, 100).into(holder.binding.movieImage)
        holder.itemView.setOnClickListener {
            /*
            val intent = Intent(holder.itemView.context, MovieDetailsActivity::class.java)
            intent.putExtra("movie_id", movieList.get(position).id)
            holder.itemView.context.startActivity(intent)
             */
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}