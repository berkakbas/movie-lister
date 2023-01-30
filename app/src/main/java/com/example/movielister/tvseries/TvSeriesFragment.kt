package com.example.movielister.tvseries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.movielister.adapter.SeriesViewPagerAdapter
import com.example.movielister.adapter.TvSeriesAdapter
import com.example.movielister.databinding.FragmentTvSeriesBinding
import com.example.movielister.util.px

class TvSeriesFragment : Fragment() {
    private var _binding: FragmentTvSeriesBinding? = null
    private val binding get() = _binding!!

    private val seriesViewModel by lazy { ViewModelProvider(requireActivity())[TvSeriesViewModel::class.java] }

    private val nextItemVisibleAmount = 250.px
    private val currentItemHorizontalMargin = 20.px
    private val pagerTranslationX = nextItemVisibleAmount - currentItemHorizontalMargin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTvSeriesBinding.inflate(inflater, container, false)

        binding.seriesViewpager.offscreenPageLimit = 2
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pagerTranslationX * position
            page.alpha = if (position == 0f) 1f else 0.5f
        }
        binding.seriesViewpager.setPageTransformer(pageTransformer)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        seriesViewModel.fetchPopularTvSeries()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            seriesViewModel.popularSeriesList.collect { popularList ->
                binding.seriesRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.seriesRecyclerView.adapter = TvSeriesAdapter(popularList)
                binding.seriesViewpager.adapter = SeriesViewPagerAdapter(popularList)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            seriesViewModel.currentSerie.collect { currentSerie ->
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }
}