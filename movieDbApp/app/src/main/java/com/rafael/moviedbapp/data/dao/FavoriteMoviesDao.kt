package com.rafael.moviedbapp.data.dao

import androidx.room.*
import java.util.List;

import com.rafael.moviedbapp.data.models.FavoriteMovie

@Dao
interface FavoriteMoviesDao {
    @Query("SELECT * FROM favoriteMovies")
    fun getAll(): List<FavoriteMovie>

    @Delete
    fun delete(favoriteMovie: FavoriteMovie)

    @Insert
    fun insert(favoriteMovie: FavoriteMovie)

    @Update
    fun update(favoriteMovie: FavoriteMovie)
}
