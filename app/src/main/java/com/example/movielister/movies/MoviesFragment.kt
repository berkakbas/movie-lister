package com.example.movielister.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielister.adapter.MovieListAdapter
import com.example.movielister.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel by lazy { ViewModelProvider(requireActivity())[MoviesViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        moviesViewModel.fetchPopularMovies()

        /*
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            moviesViewModel.popularMoviesList.collect { popularList ->
                binding.moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.moviesRecyclerView.adapter = MovieListAdapter(popularList)
            }
        }
         */

        moviesViewModel.popularMoviesList.observe(viewLifecycleOwner, Observer {popularList ->
            popularList?.let {
                binding.moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.moviesRecyclerView.adapter = MovieListAdapter(popularList)
            }
        })

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            moviesViewModel.currentMovie.collect { currentMovie ->
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}