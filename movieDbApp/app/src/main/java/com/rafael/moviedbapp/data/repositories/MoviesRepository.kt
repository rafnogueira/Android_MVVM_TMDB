package com.rafael.moviedbapp.data.repositories

import com.rafael.moviedbapp.data.dao.FavoriteMoviesDao
import com.rafael.moviedbapp.data.datasource.MovieApi
import com.rafael.moviedbapp.data.models.FavoriteMovie
import javax.inject.Inject


class MoviesRepository @Inject constructor(private val api: MovieApi, private val favoriteMoviesDao: FavoriteMoviesDao){

    fun getAllCategories() {
        var test = api
        var test2 = 0
    }

    fun getAllMoviesFromThatCategory(){
    }

    fun getTrendingMovies(){
    }

}