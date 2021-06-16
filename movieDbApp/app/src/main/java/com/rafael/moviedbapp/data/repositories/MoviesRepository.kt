package com.rafael.moviedbapp.data.repositories

import com.rafael.moviedbapp.data.dao.FavoriteMoviesDao
import com.rafael.moviedbapp.data.datasource.MovieApi
import com.rafael.moviedbapp.data.models.FavoriteMovie
import com.rafael.moviedbapp.data.models.Genre
import com.rafael.moviedbapp.data.models.Movie
import fastshop.com.moviedatabase.Models.MovieResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val api: MovieApi,
    private val favoriteMoviesDao: FavoriteMoviesDao) {

    fun getMovieDetails(movieId: String): Single<Movie> {
        return api.getMovieDetails(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getTvShowDetails(tvShowId: String): Single<Movie> {
        return api.getTvShowDetails(tvShowId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getPopularMovies(page: Int? = 1): Single<MovieResponse> = api.getPopularMovies(page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getPopularTv(page: Int? = 1): Single<MovieResponse> =
        api.getPopularTv(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getUpcoming(page: Int? = 1): Single<MovieResponse> =
        api.getUpcoming(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getNowPlaying(page: Int? = 1): Single<MovieResponse> =
        api.getNowPlaying(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getTrending(mediaType: String, timeWindow: String, page: Int? = 1): Single<MovieResponse> =
        api.getTrending(mediaType, timeWindow, page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getGenres(page: Int? = 1): Single<Genre>? =
        api.getGenres(page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getMoviesByGenre(genre_id: Int, page: Int? = 1): Single<MovieResponse?> =
        api.getMoviesByGenre(genre_id, page).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun getMoviesByQuery(query: String): Single<MovieResponse> =
        api.getMoviesByQuery(query).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    //db local
    fun getFavoriteMovies(): Single<MutableList<FavoriteMovie>> {
        return favoriteMoviesDao.getAll()
    }

    fun addFavoriteMovie(favoriteMovie: FavoriteMovie): Single<Long> {
        return Single.fromCallable {
            favoriteMoviesDao.insert(favoriteMovie);
        }
    }

    fun deleteFavoriteMovie(favoriteMovie: FavoriteMovie): Single<Int> {
        return favoriteMoviesDao.delete(favoriteMovie)
    }

}


