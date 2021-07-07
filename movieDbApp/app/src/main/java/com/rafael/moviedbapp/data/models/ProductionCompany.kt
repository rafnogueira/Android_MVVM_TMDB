package com.rafael.moviedbapp.data.models

import com.squareup.moshi.Json

data class ProductionCompanies(
    @Json(name = "id") val id: Int,
    @Json(name = "logo_path") val logoPath: String?,
    @Json(name = "Name") val name: String?,
    @Json(name = "origin_country") val originCountry: String?,
)
