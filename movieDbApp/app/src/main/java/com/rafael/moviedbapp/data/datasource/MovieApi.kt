package com.rafael.moviedbapp.data.datasource

import com.rafael.moviedbapp.data.models.Genre
import com.rafael.moviedbapp.data.models.Movie
import fastshop.com.moviedatabase.Models.MovieResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi
{
    companion object {
        //mover estes valores para dentro do script do gradle depois 😉
        var API_KEY = "e34cde97c981d0883944b9b4ee6e598e"
        var IMAGES_ENDPOINT_ORIGINAL_SIZE = "https://image.tmdb.org/t/p/original/"
        var IMAGES_ENDPOINT_500_SIZE = "https://image.tmdb.org/t/p/w500/"
        var IMAGES_ENDPOINT_1000_SIZE = "https://image.tmdb.org/t/p/w1000/"
        //var API_KEY_V4 = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMzRjZGU5N2M5ODFkMDg4Mzk0NGI5YjRlZTZlNTk4ZSIsInN1YiI6IjVhYjk0ZDc5YzNhMzY4NzFkZDAwNjkxZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.2EtB9l3xKQnbvVMMw0_RK6cGPkBpAJhdKdA_b09nNew"
    }

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") PAGE: Int? = 1, @Query("api_key") API_KEY: String? = MovieApi.API_KEY): Single<MovieResponse>

    @GET("tv/popular")
    fun getPopularTv(@Query("page") PAGE: Int? = 1,
                     @Query("api_key") API_KEY: String? = MovieApi.API_KEY): Single<MovieResponse>

    @GET("movie/upcoming")
    fun getUpcoming(@Query("page") PAGE: Int? = 1,
                    @Query("api_key") API_KEY: String? = MovieApi.API_KEY): Single<MovieResponse>


    @GET("movie/now_playing")
    fun getNowPlaying(@Query("page") PAGE: Int? = 1,
                      @Query("api_key") API_KEY: String? = MovieApi.API_KEY): Single<MovieResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: String,
                        @Query("page") PAGE: Int? = 1,
                        @Query("api_key") API_KEY: String? = MovieApi.API_KEY): Single<Movie>

    @GET("tv/{id}")
    fun getTvShowDetails(@Path("id") id: String,
                        @Query("page") PAGE: Int? = 1,
                         @Query("api_key") API_KEY: String? = MovieApi.API_KEY): Single<Movie>

    @GET("trending/{media_type}/{time_window}")
    fun getTrending(@Path("media_type") mediaType: String,
                    @Path("time_window") timeWindow: String,
                    @Query("page") page: Int? = 1,
                    @Query("api_key") API_KEY: String? = MovieApi.API_KEY): Single<MovieResponse>

    @GET("genre/movie/list")
    fun getGenres(@Query("page") PAGE: Int? = 1,
                  @Query("api_key") API_KEY: String? = MovieApi.API_KEY): Single<Genre>

    @GET("genre/{genre_id}/movies")
    fun getMoviesByGenre(@Path("genre_id") genre_id: Int,
                         @Query("page") PAGE: Int? = 1,
                         @Query("api_key") API_KEY: String? = MovieApi.API_KEY): Single<MovieResponse>

    @GET("search/movie")
    fun getMoviesSearchName(@Query("query") query: String?,
                            @Query("api_key") API_KEY: String? = MovieApi.API_KEY): Single<MovieResponse>

}