package com.rafael.moviedbapp.ui.recyclerView

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.data.datasource.MovieApi
import com.rafael.moviedbapp.data.models.Movie
import com.rafael.moviedbapp.viewModels.MainViewModel

class MovieCatalogAdapter constructor(private val context: Context,
                                      private val moviesList: MutableList<Movie>,
                                      private val viewModelPtr: MainViewModel):
    RecyclerView.Adapter<MovieCatalogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCatalogViewHolder {
        val tmpView: View = LayoutInflater.from(context).inflate(R.layout.movie_card_holder, parent, false)
        return MovieCatalogViewHolder(tmpView)
    }

    override fun onBindViewHolder(filmeHolder: MovieCatalogViewHolder, index: Int) {

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