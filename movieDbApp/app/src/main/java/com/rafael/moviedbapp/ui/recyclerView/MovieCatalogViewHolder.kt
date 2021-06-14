package com.rafael.moviedbapp.ui.recyclerView

import android.content.Intent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rafael.moviedbapp.R
import kotlinx.android.synthetic.main.movie_card_holder.view.*

class MovieCatalogViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
    var movieId: Int? = null;
    var txtViewMovieTitle: TextView? = null;
    var txtViewMovieRelease: TextView? = null;
    var imgViewPoster: ImageView? = null;
    var ratingBar: RatingBar? = null;

    init {
        this.itemView.setOnClickListener(this);

        txtViewMovieTitle = itemView.txtMovieCatalogCardTitle;
        txtViewMovieRelease = itemView.txtMovieCatalogCardReleaseDate
        imgViewPoster = itemView.imgViewMovieCatalogCoverImage
        ratingBar = itemView.ratingBarMovieCatalogCard
    }

    override fun onClick(v: View) {

    //        Toast.makeText(itemView.getContext(), "Abrindo detalhes do filme", Toast.LENGTH_SHORT).show();

//        var intent: Intent;
        //intent = Intent(itemView.context, ActivityFilmeDetails.class);
//        intent.putExtra("MovieID", );
//        view.getContext().startActivity(intent);

    }

}