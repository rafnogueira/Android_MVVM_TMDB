package com.rafael.moviedbapp.viewModels

import androidx.fragment.app.Fragment
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

//    val homeCatalog: MutableLiveData<Fragment> = MutableLiveData<Fragment>()
//    val catalogByGenre: MutableLiveData<Fragment> = MutableLiveData<Fragment>()
//    val searchCatalog: MutableLiveData<Fragment> = MutableLiveData<Fragment>()
//    val favoritesCatalog: MutableLiveData<Fragment> = MutableLiveData<Fragment>()

    fun fetchMoviesCategories() = moviesRepository.getAllCategories()

    fun fetchMovieDetails(movieId: String): Single<Movie> = moviesRepository.getMovieDetails(movieId)

    fun insertMovieToFavorites(movie: Movie){
        val favoriteMovie = FavoriteMovie(
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

//    fun homeCatalog(): MutableLiveData<Fragment> = MutableLiveData<Fragment>()
//    fun catalogByGenre(): MutableLiveData<Fragment> = MutableLiveData<Fragment>()
//    fun searchCatalog(): MutableLiveData<Fragment> = MutableLiveData<Fragment>()
//    fun favoritesCatalog(): MutableLiveData<Fragment> = MutableLiveData<Fragment>()


}