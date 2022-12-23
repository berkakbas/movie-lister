package com.example.movielister.tvseries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movielister.adapter.TvSeriesAdapter
import com.example.movielister.databinding.FragmentTvSeriesBinding

class TvSeriesFragment : Fragment() {
    private var _binding: FragmentTvSeriesBinding? = null
    private val binding get() = _binding!!

    private val seriesViewModel by lazy { ViewModelProvider(requireActivity())[TvSeriesViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTvSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        seriesViewModel.fetchPopularTvSeries()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            seriesViewModel.popularSeriesList.collect { popularList ->
                binding.seriesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.seriesRecyclerView.adapter = TvSeriesAdapter(popularList)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            seriesViewModel.currentSerie.collect { currentSerie ->
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}