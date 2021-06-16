package com.rafael.moviedbapp.ui.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.data.datasource.MovieApi
import com.rafael.moviedbapp.data.models.Movie
import com.rafael.moviedbapp.viewModels.MainViewModel

class MovieCatalogVerticalAdapter constructor(private val context: Context,
                                              private val moviesList: MutableList<Movie>,
                                              private val viewModelPtr: MainViewModel,
                                              private val isLocal: Boolean):
    RecyclerView.Adapter<MovieCatalogVerticalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCatalogVerticalHolder {
        val tmpView: View = LayoutInflater.from(context).inflate(R.layout.favorite_movie_holder, parent, false)
        return MovieCatalogVerticalHolder(tmpView)
    }

    override fun onBindViewHolder(filmeHolder: MovieCatalogVerticalHolder, index: Int) {
        val title: String =  moviesList[index].title ?: moviesList[index].name ?: ""

        filmeHolder.itemView.setOnClickListener {
            val type: String  = if (moviesList[index].name == null) "movie" else "tv"
            val id: String  = moviesList[index].id.toString()
            viewModelPtr.openDetails(type, id)
        }

        filmeHolder.txtViewMovieTitle?.setText(title)
        filmeHolder.txtViewMovieRelease?.setText("Create Date conversor")
        filmeHolder.movieId = moviesList[index].id
        filmeHolder.ratingBar?.max = 5
        filmeHolder.btnDelete?.visibility = if(isLocal) View.VISIBLE else View.GONE
        filmeHolder.btnDelete?.setOnClickListener {
            viewModelPtr.deleteMovieFromFavorites(moviesList[index])
        }

        filmeHolder.btnAddFavorite?.visibility = if(isLocal) View.GONE else View.VISIBLE
        filmeHolder.btnAddFavorite?.setOnClickListener{
            filmeHolder.btnAddFavorite?.load(R.drawable.ic_baseline_favorite_24_on)
            viewModelPtr.insertMovieToFavorites(moviesList[index])
        }

        val rating: Float = moviesList[index].voteAverage?.toFloat()?.div(2) ?: 0f
        filmeHolder.ratingBar?.rating = rating

        filmeHolder.imgViewPoster?.load(MovieApi.IMAGES_ENDPOINT_ORIGINAL_SIZE + moviesList[index].posterPath) {
            placeholder(R.drawable.selection_band_overlay)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}