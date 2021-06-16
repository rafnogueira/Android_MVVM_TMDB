package com.rafael.moviedbapp.viewModels

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafael.moviedbapp.data.models.FavoriteMovie
import com.rafael.moviedbapp.data.models.Movie
import com.rafael.moviedbapp.data.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import fastshop.com.moviedatabase.Models.MovieResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.Predicate
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :
    ViewModel() {
    //Livedata listeners
    val favoriteMovieAdded: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    val favoriteMovieDeleted: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    val fetchCategoriesErrorMessage: MutableLiveData<String> = MutableLiveData<String>()
    val openDetailsView: MutableLiveData<Pair<String, String>> = MutableLiveData<Pair<String, String>>()

    val searchedMovies: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>()
    val searchedMoviesError: MutableLiveData<String> = MutableLiveData<String>()

    val favoriteMovies: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>()

    val popularMovies: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>()
    val popularTvShows: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>()
    val upcomingMovies: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>()
    val nowPlayingMovies: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>()
    val trendingToday: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>()
    val trendingWeek: MutableLiveData<MutableList<Movie>> = MutableLiveData<MutableList<Movie>>()

    init {
//        fetchCatalog()
    }

    fun fetchCatalog() {
        fetchPopularMovies()
        fetchPopularTvShows()
        fetchUpcoming()
        fetchNowPlaying()
        fetchTrendingToday()
        fetchTrendingWeek()
    }

    fun openDetails(typeFlag: String, id: String) {
        openDetailsView.postValue(Pair(typeFlag, id))
    }

    fun fetchPopularMovies() {
        moviesRepository.getPopularMovies(1).doOnError {
            fetchCategoriesErrorMessage.postValue(it.message)
        }.subscribeBy { movieResponse ->
            movieResponse.results?.let {
                popularMovies.postValue(it.toMutableList())
            }
        }
    }

    fun fetchPopularTvShows() {
        moviesRepository.getPopularTv(1).doOnError {
            fetchCategoriesErrorMessage.postValue(it.message)
        }.subscribeBy { movieResponse ->
            movieResponse.results?.let {
                popularTvShows.postValue(it.toMutableList())
            }
        }
    }

    fun fetchUpcoming() {
        moviesRepository.getUpcoming(1).doOnError {
            fetchCategoriesErrorMessage.postValue(it.message)
        }.subscribeBy { movieResponse ->
            movieResponse.results?.let {
                upcomingMovies.postValue(it.toMutableList())
            }
        }
    }

    fun fetchNowPlaying() {
        moviesRepository.getNowPlaying(1).doOnError {
            fetchCategoriesErrorMessage.postValue(it.message)
        }.subscribeBy { movieResponse ->
            movieResponse.results?.let {
                nowPlayingMovies.postValue(it.toMutableList())
            }
        }
    }

    fun fetchTrendingToday() {
        moviesRepository.getTrending("all", "day", 1)
            .doOnError {
                fetchCategoriesErrorMessage.postValue(it.message)
            }.subscribeBy { movieResponse ->
                movieResponse.results?.let {
                    trendingToday.postValue(it.toMutableList())
                }
            }
    }

    fun fetchTrendingWeek() {
        moviesRepository.getTrending("all", "week", 1).doOnError {
            fetchCategoriesErrorMessage.postValue(it.message)
        }.subscribeBy { movieResponse ->
            movieResponse.results?.let {
                trendingWeek.postValue(it.toMutableList())
            }
        }
    }

    fun fetchMovieDetails(movieId: String): Single<Movie> =
        moviesRepository.getMovieDetails(movieId)

    fun fetchTvSbowDetails(tvShowId: String): Single<Movie> =
        moviesRepository.getTvShowDetails(tvShowId)

    fun getMoviesByQuery(query: String) {
        moviesRepository.getMoviesByQuery(query).doOnError {
            searchedMoviesError.postValue(it.localizedMessage)
        }.subscribeBy {
            if (!it.results.isNullOrEmpty())
                searchedMovies.postValue(it.results!!.toMutableList())
        }
    }

    fun insertMovieToFavorites(movie: Movie) {
        val favoriteMovie = FavoriteMovie(
            movie.id,
            movie.title,
            movie.backdropPath,
            movie.posterPath,
            movie.imdbId,
            movie.originalLanguage,
            movie.originalTitle,
            movie.overview,
            movie.popularity,
            movie.voteAverage,
            movie.name,
        )

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

    fun deleteMovieFromFavorites(movie: Movie) {
        val favoriteMovie = FavoriteMovie(
            movie.id,
            movie.title,
            movie.backdropPath,
            movie.posterPath,
            movie.imdbId,
            movie.originalLanguage,
            movie.originalTitle,
            movie.name
        )

        moviesRepository.deleteFavoriteMovie(favoriteMovie)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    favoriteMovieDeleted.postValue(false)
                },
                onSuccess = {
                    favoriteMovieDeleted.postValue(true)
                })
    }

    fun getFavoritedMovies() {
        moviesRepository.getFavoriteMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                          val error = it
                    //TODO: More error handlers
                },
                onSuccess = { favoritesList->
                    favoriteMovies.postValue(favoritesList.map {  favoriteMovie -> favoriteMovieToMovie(favoriteMovie) }.toMutableList())
                })
    }
}

fun favoriteMovieToMovie(favoriteMovie: FavoriteMovie) : Movie
{
    return Movie(favoriteMovie.movieId,
        favoriteMovie.title,
        favoriteMovie.backdropPath,
        favoriteMovie.posterPath,
        favoriteMovie.imdbId,
        favoriteMovie.originalLanguage,
        favoriteMovie.originalTitle,
        favoriteMovie.overview,
        favoriteMovie.popularity,
        favoriteMovie.voteAverage,
        null,
        null,
        favoriteMovie.name)
}
