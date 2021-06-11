package com.rafael.moviedbapp.data.models

data class Movie(
    val id: Int,
    val title: String,
    val backdropPath: String?,
    val posterPath: String?,
    val imdbId: Int,
    val originalLanguage: String,
    val originalTitle:String?)


