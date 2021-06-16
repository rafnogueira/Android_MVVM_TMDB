package com.rafael.moviedbapp.ui.main

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.ui.recyclerView.MovieCatalogAdapter
import com.rafael.moviedbapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movies_catalog_home.*
import kotlinx.android.synthetic.main.fragment_movies_catalog_home.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [MoviesCatalogHome.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MoviesCatalogHome : Fragment() {

    companion object {
        fun newInstance() = MoviesCatalogHome()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var currentRootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        currentRootView = inflater.inflate(R.layout.fragment_movies_catalog_home, container, false)

        return currentRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Observers para nos comunicarmos com a interface (View) de forma assíncrona sem travar a mainThread
        viewModel.fetchCatalog()

        viewModel.fetchCategoriesErrorMessage.observe(this.viewLifecycleOwner, Observer { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        })

        viewModel.openDetailsView.observe(this.viewLifecycleOwner, Observer { pairIdType -> //Pair de um  id do filme  e  type TV or Movie
            pairIdType?.let {
                val bundle = bundleOf(MovieDetails.TYPE_FLAG to pairIdType.first,  MovieDetails.MOVIE_ID to pairIdType.second)

                currentRootView.findNavController().navigate(R.id.action_moviesCatalogHome_to_movieDetails, bundle)
            }
        })

        viewModel.popularMovies.observe(this.viewLifecycleOwner, Observer { moviesList ->
            moviesList?.let {
                recyclerViewPopularMovies.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recyclerViewPopularMovies.adapter = MovieCatalogAdapter(requireContext(), it, viewModel)

                showCatalog()
            }
        })
        viewModel.popularTvShows.observe(this.viewLifecycleOwner, Observer { moviesList ->
            moviesList?.let {
                recyclerViewPopularTv.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recyclerViewPopularTv.adapter = MovieCatalogAdapter(requireContext(), it, viewModel)

                showCatalog()
            }
        })
        viewModel.upcomingMovies.observe(this.viewLifecycleOwner, Observer { moviesList ->
            moviesList?.let {
                recyclerViewPopularUpcoming.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recyclerViewPopularUpcoming.adapter = MovieCatalogAdapter(requireContext(), it, viewModel)

                showCatalog()
            }
        })
        viewModel.nowPlayingMovies.observe(this.viewLifecycleOwner, Observer { moviesList ->
            moviesList?.let {
                recyclerViewNowPlayingCinema.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recyclerViewNowPlayingCinema.adapter = MovieCatalogAdapter(requireContext(), it, viewModel)

                showCatalog()
            }
        })
        viewModel.trendingToday.observe(this.viewLifecycleOwner, Observer { moviesList ->
            moviesList?.let {
                recyclerViewTrendingToday.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recyclerViewTrendingToday.adapter = MovieCatalogAdapter(requireContext(), it, viewModel)

                showCatalog()
            }
        })
        viewModel.trendingWeek.observe(this.viewLifecycleOwner, Observer { moviesList ->
            moviesList?.let {
                recyclerViewTrendingWeek.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                recyclerViewTrendingWeek.adapter = MovieCatalogAdapter(requireContext(), it, viewModel)

                showCatalog()
            }
        })

        viewModel.favoriteMovieAdded.observe(this.viewLifecycleOwner, Observer { isAddedFlag ->
            isAddedFlag?.let { added ->
                if (added) {
                    btnMoviesDetailsFavorite.setSelected(true)
                    btnMoviesDetailsFavorite.setBackgroundTintList(
                        ColorStateList.valueOf(
                            resources.getColor(
                                R.color.FavoritosCor
                            )
                        )
                    );
                    Toast.makeText(
                        requireContext(),
                        "Filme adicionado aos favoritos sucesso!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    btnMoviesDetailsFavorite.setBackgroundTintList(
                        ColorStateList.valueOf(
                            resources.getColor(
                                R.color.white
                            )
                        )
                    );
                    Toast.makeText(
                        requireContext(),
                        "Erro ao salvar seu filme ou filme já está salvo no banco local!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    fun showCatalog()
    {
        currentRootView.moviesCatalogHomeLayout?.visibility = View.VISIBLE
        currentRootView.moviesCatalogLoadingProgressBar?.visibility = View.GONE

    }


}