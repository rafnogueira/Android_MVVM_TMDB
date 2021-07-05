package com.rafael.moviedbapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "favoriteMovies")
data class FavoriteMovie(
    @PrimaryKey
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String? = null,
    @ColumnInfo(name = "poster_path") val posterPath: String? = null,
    @ColumnInfo(name = "imdb_id") val imdbId: String? = null,
    @ColumnInfo(name = "original_language") val originalLanguage: String? = null,
    @ColumnInfo(name = "original_title") val originalTitle:String? = null,
    @ColumnInfo(name = "overview") val overview:String? = null,
    @ColumnInfo(name = "popularity") val popularity:String? = null,
    @ColumnInfo(name = "vote_average") val voteAverage:String? = null,
    @ColumnInfo(name = "name") val name:String? = null,
    @ColumnInfo(name = "release_date") val releaseDate: String? = null)
