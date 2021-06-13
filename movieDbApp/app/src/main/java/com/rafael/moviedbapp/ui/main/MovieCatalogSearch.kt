package com.rafael.moviedbapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.viewModels.MovieSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieCatalogSearch : Fragment() {

    companion object {
        fun newInstance() = MovieCatalogSearch()
    }

    private lateinit var viewModel: MovieSearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieSearchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}