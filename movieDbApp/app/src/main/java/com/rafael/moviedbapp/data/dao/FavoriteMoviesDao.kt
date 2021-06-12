package com.rafael.moviedbapp.data.dao

import androidx.room.*
import java.util.List;

import com.rafael.moviedbapp.data.models.FavoriteMovie
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
interface FavoriteMoviesDao {
    @Query("SELECT * FROM favoriteMovies")
    fun getAll(): Single<List<FavoriteMovie>>

    @Delete
    fun delete(favoriteMovie: FavoriteMovie): Int

    @Insert
    fun insert(favoriteMovie: FavoriteMovie): Long

    @Update
    fun update(favoriteMovie: FavoriteMovie): Int

}
