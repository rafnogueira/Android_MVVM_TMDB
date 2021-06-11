package fastshop.com.moviedatabase.Models

import com.rafael.moviedbapp.data.models.Movie

data class MovieResponse (
    var results: List<Movie>?,
    var page: Int?,
    var totalResults: Int?,
    var totalPages: Int?)