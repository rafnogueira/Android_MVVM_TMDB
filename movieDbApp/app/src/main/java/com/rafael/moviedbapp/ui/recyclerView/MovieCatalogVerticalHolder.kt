package com.rafael.moviedbapp.ui.recyclerView

import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rafael.moviedbapp.R
import kotlinx.android.synthetic.main.favorite_movie_holder.view.*
import kotlinx.android.synthetic.main.fragment_movies_catalog_favorites.view.*
import kotlinx.android.synthetic.main.movie_card_holder.view.*

class MovieCatalogVerticalHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
    var movieId: Int? = null;
    var txtViewMovieTitle: TextView? = null;
    var txtViewMovieRelease: TextView? = null;
    var imgViewPoster: ImageView? = null;
    var ratingBar: RatingBar? = null;
    var btnDelete: ImageButton? = null;
    var btnAddFavorite: ImageButton? = null;

    init {
        txtViewMovieTitle = itemView.txtCatalogFavoriteCardTitle
        txtViewMovieRelease = itemView.txtCatalogFavoriteCardReleaseDate
        imgViewPoster = itemView.imgViewCatalogMovieFavoriteCoverImage
        ratingBar = itemView.ratingBarCatalogFavoriteCard
        btnDelete = itemView.btnFavoriteMovieDelete
        btnAddFavorite = itemView.btnFavoriteMovieInsert
    }
}