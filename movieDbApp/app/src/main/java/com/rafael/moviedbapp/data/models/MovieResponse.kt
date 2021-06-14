package fastshop.com.moviedatabase.Models

import com.rafael.moviedbapp.data.models.Movie
import com.squareup.moshi.Json

data class MovieResponse (
    @Json(name = "page") var page: Int?,
    @Json(name = "results") var results: List<Movie>?,
    @Json(name = "total_results") var totalResults: Int?,
    @Json(name = "total_pages") var totalPages: Int?)