package com.example.movielister.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movielister.adapter.MovieListAdapter
import com.example.movielister.databinding.FragmentMoviesBinding
import com.example.movielister.model.MovieModel

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel by lazy { ViewModelProvider(requireActivity())[MoviesViewModel::class.java] }
    lateinit var selectedMovie: MovieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root = binding.root

        moviesViewModel.fetchPopularMovies()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            moviesViewModel.popularMoviesList.collect { popularList ->
                binding.moviesRecyclerView.adapter = MovieListAdapter(popularList)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            moviesViewModel.currentMovie.collect { currentMovie ->
                selectedMovie = currentMovie
            }
        }

        return root
    }
}