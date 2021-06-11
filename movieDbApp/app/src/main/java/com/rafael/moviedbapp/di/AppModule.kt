package com.rafael.moviedbapp.di

import android.content.Context
import androidx.room.Room
import com.rafael.moviedbapp.data.dao.FavoriteMoviesDao
import com.rafael.moviedbapp.data.datasource.AppDatabase
import com.rafael.moviedbapp.data.datasource.MovieApi
import com.rafael.moviedbapp.data.repositories.MoviesRepository
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

//Objeto estático do kotlin com o Dagger Hilt, para providênciar os módulos necessários do app
//Todos os provedores estão encapsulados aqui, mas poderia haver uma separação para maior organização em projetos maiores
@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    //Provide API Endpoints service
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApi{
        return retrofit.create(MovieApi::class.java)
    }

    //Provide o banco de dados local do app
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase = Room.databaseBuilder(
        appContext,
        AppDatabase::class.java,
        "fastshop-movies-db"
    ).build()

    //Provide repositórios para os viewModels
    @Provides
    fun provideMoviesRepository(api: MovieApi,
                                favoriteMoviesDao: FavoriteMoviesDao
    ): MoviesRepository = MoviesRepository(api, favoriteMoviesDao)


    //Provide Dao para o banco de dados local para os repositórios
    @Provides
    fun provideFavoriteMoviesDao(appDatabase: AppDatabase): FavoriteMoviesDao = appDatabase.favoriteMoviesDao()

}

