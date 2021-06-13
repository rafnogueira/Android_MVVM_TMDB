package com.rafael.moviedbapp.ui.main

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.BlurTransformation
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.data.datasource.MovieApi
import com.rafael.moviedbapp.data.models.Movie
import com.rafael.moviedbapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.movie_details_fragment.*
import kotlinx.android.synthetic.main.movie_details_fragment.view.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var currentRootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        currentRootView = inflater.inflate(R.layout.main_fragment, container, false)

        currentRootView = inflater.inflate(R.layout.movie_details_fragment, container, false)

        return currentRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Observers para nos comunicarmos com a interface (View) de forma assíncrona sem travar a mainThread
        val favoritedMovieObserver = viewModel.favoriteMovieAdded.observeForever { isAddedFlag ->
            isAddedFlag?.let { added ->
                if (added) {
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
                } else{
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
        }

        getDbValues()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun insertDbTest() {
        val movieTest = Movie(
            1,
            "Movie Test",
            "",
            "",
            "asd",
            "",
            "",
            null,
            null,
            null,
            null,
            null
        );

        viewModel.insertMovieToFavorites(movieTest)
        val test = 0;
    }

    private fun getDbValues() {
//        val clubeLutaId = "550"
        val clubeLutaId = "686"

        viewModel.fetchMovieDetails(clubeLutaId).subscribeBy(
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

                    currentRootView?.txtViewMovieDetailsPopularity.text = movie.popularity ?: ""

                    btnMoviesDetailsFavorite.setOnClickListener {
                        viewModel.insertMovieToFavorites(movie)
                    }

                }
            }
        )


    }

}