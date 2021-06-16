package com.rafael.moviedbapp.ui.recyclerView

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.movie_card_holder.view.*

class MovieCatalogViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
    var movieId: Int? = null;
    var txtViewMovieTitle: TextView? = null;
    var txtViewMovieRelease: TextView? = null;
    var imgViewPoster: ImageView? = null;
    var ratingBar: RatingBar? = null;

    init {
        txtViewMovieTitle = itemView.txtMovieCatalogCardTitle;
        txtViewMovieRelease = itemView.txtMovieCatalogCardReleaseDate
        imgViewPoster = itemView.imgViewMovieCatalogCoverImage
        ratingBar = itemView.ratingBarMovieCatalogCard
    }
}