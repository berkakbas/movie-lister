package com.example.movielister.tvseries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movielister.databinding.FragmentTvSeriesBinding

class TvSeriesFragment : Fragment() {
    private var _binding: FragmentTvSeriesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentTvSeriesBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }
}