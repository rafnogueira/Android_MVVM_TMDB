package com.rafael.moviedbapp.data.datasource

import com.rafael.moviedbapp.data.models.Movie
import fastshop.com.moviedatabase.Models.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi
{
    companion object{
        //Eu poderia mover estes valores para dentro do script do gradle depois 😉
        var BASE_URL = "http://api.themoviedb.org/3/"
        var API_KEY = "e34cde97c981d0883944b9b4ee6e598e"
        var API_KEY_V4 = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMzRjZGU5N2M5ODFkMDg4Mzk0NGI5YjRlZTZlNTk4ZSIsInN1YiI6IjVhYjk0ZDc5YzNhMzY4NzFkZDAwNjkxZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.H2LM0wTPDVlENrr0TKqO8UATTa77Q8-5He2iAk2A3Z8"
    }

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") API_KEY: String?): Call<MovieResponse?>?

    @GET("movie/upcoming")
    fun getLatestMovies(@Query("api_key") API_KEY: String?): Call<MovieResponse?>?

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") API_KEY: String?): Call<MovieResponse?>?

    @GET("movie/now_playing")
    fun getNowPlaying(@Query("api_key") API_KEY: String?): Call<MovieResponse?>?

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Int, @Query("api_key") API_KEY: String?): Call<Movie?>?

//    @GET("tv/popular")
//    fun getSeriesPopular(@Query("api_key") API_KEY: String?): Call<TVShowResponse?>?
//
//    @GET("tv/top_rated")
//    fun getSeriesRating(@Query("api_key") API_KEY: String?): Call<TVShowResponse?>?

//    @GET("tv/{tv_id}")
//    fun getShowDetails(@Path("tv_id") tv_id: Int, @Query("api_key") API_KEY: String?): Call<TVShow?>?
//
//    @GET("genre/movie/list")
//    fun getGenres(@Query("api_key") API_KEY: String?): Call<Genero?>?

    @GET("genre/{genre_id}/movies")
    fun getMoviesByGenre(
        @Path("genre_id") genre_id: Int,
        @Query("api_key") API_KEY: String?
    ): Call<MovieResponse?>?

    @GET("search/movie")
    fun getMoviesSearchName(
        @Query("api_key") API_KEY: String?,
        @Query("query") query: String?
    ): Call<MovieResponse?>?

}