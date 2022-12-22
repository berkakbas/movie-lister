package com.example.movielister.tvseries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movielister.databinding.FragmentTvSeriesBinding
import com.example.movielister.model.TvSeriesModel

class TvSeriesFragment : Fragment() {
    private var _binding: FragmentTvSeriesBinding? = null
    private val binding get() = _binding!!

    private val seriesViewModel by lazy { ViewModelProvider(requireActivity())[TvSeriesViewModel::class.java] }
    lateinit var selectedSerie: TvSeriesModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTvSeriesBinding.inflate(inflater, container, false)

        seriesViewModel.fetchPopularTvSeries()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            seriesViewModel.currentSerie.collect { currentSerie ->
                selectedSerie = currentSerie
            }
        }

        return binding.root
    }
}