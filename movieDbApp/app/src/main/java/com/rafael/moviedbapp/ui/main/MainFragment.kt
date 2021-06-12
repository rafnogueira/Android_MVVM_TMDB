package com.rafael.moviedbapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.RoomDatabase
import coil.load
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.data.datasource.MovieApi
import com.rafael.moviedbapp.data.models.Movie
import com.rafael.moviedbapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.android.synthetic.main.main_fragment.*

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
        currentRootView = inflater.inflate(R.layout.main_fragment, container, false)
        return currentRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Observers para nos comunicarmos com a interface (View) de forma assíncrona sem travar a mainThread
        val favoritedMovieObserver = viewModel.favoriteMovieAdded.observeForever { isAddedFlag ->
            isAddedFlag?.let {
                if (it)
                    Toast.makeText(
                        requireContext(),
                        "Filme adicionado aos favoritos sucesso!",
                        Toast.LENGTH_LONG
                    ).show()
                else
                    Toast.makeText(
                        requireContext(),
                        "Ocorreu um erro ao salvar seu filme no banco local!",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.recoverDb).setOnClickListener {
            getDbValues()
        }

    }

    private fun insertDbTest() {
        val movieTest = Movie(
            1,
            "Movie Test",
            "",
            "",
            "asd",
            "",
            ""
        );

        viewModel.insertMovieToFavorites(movieTest)
        val test = 0;
    }

    private fun getDbValues() {

        val clubeLutaId = "550"

        viewModel.fetchMovieDetails(clubeLutaId).subscribeBy(
            onError = {
                Toast.makeText(
                    requireContext(),
                    "Houve um erro ao requisitar o filme " + it.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
            },
            onSuccess = { movie ->
                Toast.makeText(requireContext(), "Sucesso filme:" + movie.title, Toast.LENGTH_LONG).show()
                currentRootView?.findViewById<ImageView>(R.id.imageTest).load(MovieApi.IMAGES_ENDPOINT_ORIGINAL_SIZE+movie.backdropPath) {
                    crossfade(true)
                    placeholder(R.drawable.selection_band_overlay)
                }

            }
        )


    }

}