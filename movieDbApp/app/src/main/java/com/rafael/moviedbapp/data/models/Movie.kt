package com.rafael.moviedbapp.data.models

import com.squareup.moshi.Json

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

    @Json(name = "name") val name: String?, //TV shows vem de forma diferente, mas usei apenas esta variável para controle, pois ela vem null quando é filme
    //@Json(name = "release_date") val releaseDate: Date?  //TODO: create a custom converter
    private val isFavorite: Boolean = false)