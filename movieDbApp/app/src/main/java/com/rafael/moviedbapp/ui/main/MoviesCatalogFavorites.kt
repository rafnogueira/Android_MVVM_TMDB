package com.rafael.moviedbapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.ui.recyclerView.MovieCatalogVerticalAdapter
import com.rafael.moviedbapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies_catalog_favorites.*

@AndroidEntryPoint
class MoviesCatalogFavorites : Fragment() {
    companion object {
        fun newInstance() = MoviesCatalogFavorites()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var currentRootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentRootView = inflater.inflate(R.layout.fragment_movies_catalog_favorites, container, false)

        return currentRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.favoriteMovies.observe(viewLifecycleOwner, Observer {
            recyclerViewFavorites.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerViewFavorites.adapter = MovieCatalogVerticalAdapter(requireContext(), it, viewModel, true)
        })

        viewModel.favoriteMovieDeleted.observe(viewLifecycleOwner, Observer {
            viewModel.getFavoritedMovies()
            Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
        })

        viewModel.openDetailsView.observe(
            this.viewLifecycleOwner,
            Observer { pairIdType -> //Pair de um  id do filme  e  type TV or Movie
                pairIdType?.let {
                    val bundle = bundleOf(
                        MovieDetails.TYPE_FLAG to pairIdType.first,
                        MovieDetails.MOVIE_ID to pairIdType.second
                    )

                    currentRootView.findNavController().navigate(R.id.action_moviesCatalogFavorites_to_movieDetails, bundle)
                }
            })


        viewModel.getFavoritedMovies()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}