package com.rafael.moviedbapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.ui.recyclerView.MovieCatalogVerticalAdapter
import com.rafael.moviedbapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_search.*

@AndroidEntryPoint
class MovieCatalogSearch : Fragment() {

    companion object {
        fun newInstance() = MovieCatalogSearch()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var currentRootView: View

    // Job delayed para pesquisar
    private val handler: Handler = Handler()
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentRootView = inflater.inflate(R.layout.fragment_movie_search, container, false)
        return currentRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.searchedMovies.observe(this.viewLifecycleOwner, Observer { movies ->
            recyclerViewMovieSearch.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recyclerViewMovieSearch.adapter = MovieCatalogVerticalAdapter(requireContext(), movies, viewModel, false)
        })

        viewModel.searchedMoviesError.observe(this.viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "Erro ao pesquisar filmes" + it, Toast.LENGTH_LONG).show()
        })

            searchViewMovieSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextChange(newText: String): Boolean {
                    runnable = Runnable { viewModel.getMoviesByQuery(searchViewMovieSearch?.query.toString() ?: "") }
                    handler.postDelayed(runnable, 2000)

                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.getMoviesByQuery(searchViewMovieSearch?.query.toString() ?: "")
                    return true
                }

            })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}