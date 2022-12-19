package com.example.movielister.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movielister.adapter.MovieListAdapter
import com.example.movielister.databinding.FragmentMoviesBinding
import com.example.movielister.network.MoviesNetwork

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root = binding.root

        val list = MoviesNetwork.fetchPopularMovies()
        binding.moviesRecyclerView.adapter = MovieListAdapter(list)

        return root
    }
}