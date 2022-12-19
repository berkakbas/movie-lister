package com.example.movielister.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movielister.adapter.MovieListAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movielister.databinding.FragmentMoviesBinding
import com.example.movielister.network.MoviesNetwork

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel by lazy { ViewModelProvider(requireActivity()) [MoviesViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        moviesViewModel.fetchPopularMovies()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            moviesViewModel.popularMovieList.collect { popularList ->
                binding.moviesRecyclerView.adapter = MovieListAdapter(popularList)
            }
        }

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }
}