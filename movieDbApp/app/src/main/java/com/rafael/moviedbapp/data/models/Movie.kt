package com.rafael.moviedbapp.data.models

import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import java.util.*

data class Movie(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "imdb_id") val imdbId: String?,
    @Json(name = "original_language") val originalLanguage: String?,
    @Json(name = "original_title") val originalTitle:String?,
    @Json(name = "overview") val overview:String?,
    @Json(name = "popularity") val popularity:String?,
    @Json(name = "vote_average") val voteAverage:String?,
    @Json(name = "production_companies") val productionCompanies :List<ProductionCompanies>?,
    @Json(name = "genres") val genres: List<Genre>?,
    //@Json(name = "release_date") val releaseDate: Date?  //TODO: create a custom converter

    )