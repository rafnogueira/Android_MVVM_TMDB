package com.rafael.moviedbapp.data.repositories

import com.rafael.moviedbapp.data.datasource.MovieApi
import javax.inject.Inject


class MoviesRepository @Inject constructor(val api: MovieApi ){

    fun getAllCategories() {
        var test = api
        var test2 = 0
    }

    fun getAllMoviesFromThatCategory(){
    }

    fun getTrendingMovies(){
    }

}