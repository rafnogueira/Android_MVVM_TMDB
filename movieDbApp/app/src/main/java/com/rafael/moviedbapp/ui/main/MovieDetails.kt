package com.rafael.moviedbapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.data.datasource.MovieApi
import com.rafael.moviedbapp.data.models.Movie
import com.rafael.moviedbapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.view.*

@AndroidEntryPoint
class MovieDetails : Fragment() {

    companion object {
        fun newInstance() = MoviesCatalogHome()

        const val MOVIE_ID = "MOVIE_ID"
        const val TYPE_FLAG = "TYPE_FLAG"
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var currentRootView: View
    private var movieId: String? = null
    private var typeFLag: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentRootView = inflater.inflate(R.layout.fragment_movie_details, container, false)
        return currentRootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        movieId = arguments?.getString(MOVIE_ID)
        typeFLag = arguments?.getString(TYPE_FLAG)

        viewModel.favoriteMovieAdded.observe(this.viewLifecycleOwner, Observer {
            it?.let { isAdded ->
                if(isAdded)
                    Toast.makeText(requireContext(), "Movie Added to local favorites!", Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(requireContext(), "Movie failed to be added on local favorites!", Toast.LENGTH_LONG).show()
            }
        })

        getMovieDetails()
    }

    private fun getMovieDetails() {
        movieId?.let { movieId ->
            if (typeFLag == "movie")
                viewModel.fetchMovieDetails(movieId).subscribeBy( onError = {
                        Toast.makeText(
                            requireContext(),
                            "Houve um erro ao conseguir dados do filme " + it.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()

                    }, onSuccess = {
                        bindMovieData(it)
                    })
            else
                viewModel.fetchTvSbowDetails(movieId).subscribeBy(onError = {
                        Toast.makeText(
                            requireContext(),
                            "Houve um erro ao conseguir dados do filme " + it.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()

                    }, onSuccess = {
                        bindMovieData(it)
                    })
        }
    }

    private fun bindMovieData(movie: Movie) {
        currentRootView.imgViewMovieDetailsCover.load(MovieApi.IMAGES_ENDPOINT_ORIGINAL_SIZE + movie.posterPath)
        {
            placeholder(R.drawable.selection_band_overlay)
        }

        currentRootView.imgViewMovieDetailsBackdrop.load(MovieApi.IMAGES_ENDPOINT_ORIGINAL_SIZE + movie.backdropPath)
        {
            crossfade(true)
            placeholder(R.drawable.selection_band_overlay)
        }

        currentRootView.txtViewMovieDetailsTitle.text = movie.title ?: movie.name
        currentRootView.txtMovieDetailsSinopseDesc.text = movie.overview ?: ""
        currentRootView.txtViewMovieDetailsUserRating.text = "User rating: " + movie.voteAverage

        val genres = movie.genres?.map { Genre -> Genre.name }
        currentRootView.txtViewMovieDetailsGenres.text = genres?.toString() ?: ""
        currentRootView.txtViewMovieDetailsPopularity.text = "Popularity" + movie.popularity

        currentRootView.movieDetailsProgressWidget.visibility = View.GONE
        currentRootView.movieDetailsScrollView.visibility = View.VISIBLE

        btnMoviesDetailsFavorite.setOnClickListener {
            viewModel.insertMovieToFavorites(movie)

            it.isSelected = true
        }
    }

}