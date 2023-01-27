package com.example.movielister.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movielister.databinding.MovieHolderBinding
import com.example.movielister.model.MovieModel
import com.example.movielister.movies.MovieDetailsActivity
import com.squareup.picasso.Picasso

class MovieViewPagerAdapter(private val movieList: List<MovieModel>) : RecyclerView.Adapter<MovieViewPagerAdapter.ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val binding = MovieHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewPagerHolder(private var movieHolderBinding: MovieHolderBinding) : RecyclerView.ViewHolder(movieHolderBinding.root) {
        fun bind(movie: MovieModel) {
            movieHolderBinding.movieImage.setOnClickListener {
                val intent = Intent(movieHolderBinding.root.context, MovieDetailsActivity::class.java)
                intent.putExtra("movie", movie)
                movieHolderBinding.root.context.startActivity(intent)
            }

            movieHolderBinding.movieName.text = movie.title
            Picasso.get().load(movie.imageUrl + movie.poster_path).into(movieHolderBinding.movieImage)
        }
    }
}
