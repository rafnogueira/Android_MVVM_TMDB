package com.rafael.moviedbapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rafael.moviedbapp.R
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [MoviesCatalogGenre.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MoviesCatalogGenre : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_catalog_genre, container, false)
    }

    companion object {
        fun newInstance() = MoviesCatalogGenre()
    }
}