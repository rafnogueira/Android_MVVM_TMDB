package com.rafael.moviedbapp.di

import android.content.Context
import androidx.room.Room
import com.rafael.moviedbapp.data.dao.FavoriteMoviesDao
import com.rafael.moviedbapp.data.datasource.AppDatabase
import com.rafael.moviedbapp.data.datasource.MovieApi
import com.rafael.moviedbapp.data.repositories.MoviesRepository
import com.rafael.moviedbapp.data.utils.DateConverterJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(DateConverterJson())
        .build()

    //API Endpoints
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApi{
        return retrofit.create(MovieApi::class.java)
    }


    //Room db
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "movies-db"
    ).build()

    //ViewModels
    @Provides
    fun provideMoviesRepository(api: MovieApi,
                                favoriteMoviesDao: FavoriteMoviesDao
    ): MoviesRepository = MoviesRepository(api, favoriteMoviesDao)

    //Dao
    @Provides
    fun provideFavoriteMoviesDao(appDatabase: AppDatabase): FavoriteMoviesDao = appDatabase.favoriteMoviesDao()

}

