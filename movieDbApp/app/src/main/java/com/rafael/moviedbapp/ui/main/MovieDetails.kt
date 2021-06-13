package com.rafael.moviedbapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import coil.load
import coil.transform.BlurTransformation
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.data.datasource.MovieApi
import com.rafael.moviedbapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.fragment_movie_details.view.*

private const val MOVIE_ID = "MOVIE_ID"

@AndroidEntryPoint
class MovieDetails : Fragment() {

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(movieId: String) =
            MoviesCatalogHome().apply {
                arguments = Bundle().apply {
                    putString(MOVIE_ID, movieId)
                }
            }
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var currentRootView: View
    private var movieId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        currentRootView = inflater.inflate(R.layout.fragment_movie_details, container, false)
        return currentRootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getString(MOVIE_ID)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        movieId?.let {
            getMovieDetails()
        }
    }

    private fun getMovieDetails() {
        viewModel.fetchMovieDetails(movieId!!).subscribeBy(
            onError = {
                Toast.makeText(
                    requireContext(),
                    "Houve um erro ao requisitar o filme " + it.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
            },
            onSuccess = {
                it?.let { movie ->
                    currentRootView?.findViewById<ImageView>(R.id.imgViewMovieDetailsCover)
                        .load(MovieApi.IMAGES_ENDPOINT_ORIGINAL_SIZE + movie.posterPath) {
                            placeholder(R.drawable.selection_band_overlay)
                        }

                    currentRootView?.findViewById<ImageView>(R.id.imgViewMovieDetailsBackdrop)
                        .load(MovieApi.IMAGES_ENDPOINT_ORIGINAL_SIZE + movie.backdropPath) {
                            crossfade(true)
                            BlurTransformation(requireContext(), 10f, 1f)
                            placeholder(R.drawable.selection_band_overlay)
                        }

                    currentRootView?.txtViewMovieDetailsTitle.text = movie.title ?: ""
                    currentRootView?.txtMovieDetailsSinopseDesc.text = movie.overview ?: ""
                    currentRootView?.txtViewMovieDetailsUserRating.text =
                        "User rating: " + movie.voteAverage ?: ""

                    val genres = movie.genres?.map { Genre -> Genre.name }
                    currentRootView?.txtViewMovieDetailsGenres.text = genres?.toString() ?: ""

                    currentRootView?.txtViewMovieDetailsPopularity.text = "Popularity" + movie.popularity ?: ""

                    btnMoviesDetailsFavorite.setOnClickListener {
                        viewModel.insertMovieToFavorites(movie)
                    }
                }
            }
        )
    }

}