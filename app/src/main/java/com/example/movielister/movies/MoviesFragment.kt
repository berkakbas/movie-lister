package com.example.movielister.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.movielister.adapter.MovieListAdapter
import com.example.movielister.adapter.MovieViewPagerAdapter
import com.example.movielister.databinding.FragmentMoviesBinding
import com.example.movielister.util.px
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val moviesViewModel by viewModels<MoviesViewModel>()

    private val nextItemVisibleAmount = 250.px
    private val currentItemHorizontalMargin= 20.px
    private val pagerTranslationX = nextItemVisibleAmount - currentItemHorizontalMargin

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        binding.viewpager.offscreenPageLimit = 2

        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pagerTranslationX * position
            page.alpha = if (position == 0f) 1f else 0.5f
        }
        binding.viewpager.setPageTransformer(pageTransformer)

        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        moviesViewModel.fetchPopularMovies()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            moviesViewModel.popularMoviesList.collect { popularList ->
                binding.moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.moviesRecyclerView.adapter = MovieListAdapter(popularList)

                binding.viewpager.adapter = MovieViewPagerAdapter(popularList)
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

}