package com.example.movielister.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielister.adapter.MovieListAdapter
import com.example.movielister.databinding.FragmentSearchBinding
import com.jakewharton.rxbinding3.widget.textChanges
import java.util.concurrent.TimeUnit


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val searchViewModel by lazy { ViewModelProvider(requireActivity())[SearchViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindTextListener()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            searchViewModel.movieResults.collect { movieList ->
                binding.searchRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.searchRecyclerView.adapter = MovieListAdapter(movieList)
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun bindTextListener() {
        binding.searchEditText.textChanges()
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribe { charSequence ->
                searchViewModel.searchMovie(charSequence.toString())
            }
    }

}