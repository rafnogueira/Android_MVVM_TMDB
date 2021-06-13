package com.rafael.moviedbapp.ui.main

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.data.models.Movie
import com.rafael.moviedbapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*


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

        currentRootView = inflater.inflate(R.layout.fragment_movie_details, container, false)

        return currentRootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Observers para nos comunicarmos com a interface (View) de forma assíncrona sem travar a mainThread
        viewModel.favoriteMovieAdded.observeForever { isAddedFlag ->
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
}