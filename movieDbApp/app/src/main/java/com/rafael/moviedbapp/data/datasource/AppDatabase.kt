package com.rafael.moviedbapp.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rafael.moviedbapp.data.dao.FavoriteMoviesDao
import com.rafael.moviedbapp.data.models.FavoriteMovie
import com.rafael.moviedbapp.data.utils.DateConverter

@Database(entities = arrayOf(FavoriteMovie::class), version = 1)

@TypeConverters(DateConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteMoviesDao(): FavoriteMoviesDao

}