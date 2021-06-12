package com.rafael.moviedbapp.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafael.moviedbapp.data.models.FavoriteMovie
import com.rafael.moviedbapp.data.models.Movie
import com.rafael.moviedbapp.data.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {

    //Livedata listeners
    val favoriteMovieAdded: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


    fun fetchMoviesCategories() = moviesRepository.getAllCategories()

    fun fetchMovieDetails(movieId: String): Single<Movie> = moviesRepository.getMovieDetails(movieId)

    fun insertMovieToFavorites(movie: Movie){
        val favoriteMovie = FavoriteMovie(0,
            movie.id,
            movie.title!!,
            movie.backdropPath,
            movie.posterPath,
            movie.imdbId!!,
            movie.originalLanguage!!,
            movie.originalTitle)

        moviesRepository.addFavoriteMovie(favoriteMovie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                          favoriteMovieAdded.postValue(false)
                },
                onSuccess = {
                    favoriteMovieAdded.postValue(true)
                })


    }

    fun getFavoritedMovies() = moviesRepository.getFavoriteMovies()

}