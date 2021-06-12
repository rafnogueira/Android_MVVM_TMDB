package com.rafael.moviedbapp.data.repositories

import android.util.Log
import com.rafael.moviedbapp.data.dao.FavoriteMoviesDao
import com.rafael.moviedbapp.data.datasource.MovieApi
import com.rafael.moviedbapp.data.models.FavoriteMovie
import com.rafael.moviedbapp.data.models.Movie
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.List
import javax.inject.Inject
class MoviesRepository @Inject constructor(private val api: MovieApi, private val favoriteMoviesDao: FavoriteMoviesDao){

    fun getMovieDetails(movieId: String): Single<Movie>{
        return api.getMovieDetails(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAllCategories() {}

    fun getAllMoviesFromThatCategory(){}

    fun getTrendingMovies(){}

    //db local
    fun getFavoriteMovies(): Single<List<FavoriteMovie>> = favoriteMoviesDao.getAll()

    fun addFavoriteMovie(favoriteMovie: FavoriteMovie): Single<Long> {
        return Single.fromCallable {
            favoriteMoviesDao.insert(favoriteMovie);
        }
    }

//    fun deleteFavoriteMovie(favoriteMovie: FavoriteMovie): Single<Int> = favoriteMoviesDao.delete(favoriteMovie)

}


