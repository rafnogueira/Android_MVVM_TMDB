package com.rafael.moviedbapp.data.models

import com.squareup.moshi.Json

data class MovieImagesResponse(@Json(name = "id") val movieId: String,
                               @Json(name = "backdrops") val backdrops: List<MovieImage>,
                               @Json(name = "posters") val posters: List<MovieImage>)

data class MovieImage(@Json(name = "aspect_ratio") val aspect_ratio: Number?,
                      @Json(name = "file_path") val file_path: String?,
                      @Json(name = "height") val height: String?,
                      @Json(name = "iso_639_1") val iso_639_1: String?,
                      @Json(name = "vote_average") val vote_average: String?,
                      @Json(name = "vote_count") val vote_count: String?,
                      @Json(name = "width") val width: String?)