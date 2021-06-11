package com.rafael.moviedbapp.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafael.moviedbapp.data.dao.FavoriteMoviesDao
import com.rafael.moviedbapp.data.models.FavoriteMovie

@Database(entities = arrayOf(FavoriteMovie::class), version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteMoviesDao(): FavoriteMoviesDao

}