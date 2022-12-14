package com.example.movielister.factory

import androidx.fragment.app.Fragment
import com.example.movielister.R
import com.example.movielister.movies.MoviesFragment
import com.example.movielister.profile.ProfileFragment
import com.example.movielister.search.SearchFragment
import com.example.movielister.tvseries.TvSeriesFragment

object BasicFragmentFactory {
    fun generateFragment (id: Int): Fragment {
        return when (id) {
            R.id.movies -> MoviesFragment()
            R.id.tvSeries -> TvSeriesFragment()
            R.id.search -> SearchFragment()
            R.id.profile -> ProfileFragment()
            else -> throw NotImplementedError()
        }
    }
}