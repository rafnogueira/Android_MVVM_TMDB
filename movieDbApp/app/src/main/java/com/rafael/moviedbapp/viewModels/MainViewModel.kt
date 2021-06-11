package com.rafael.moviedbapp.viewModels

import androidx.lifecycle.ViewModel
import com.rafael.moviedbapp.data.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val moviesRepository: MoviesRepository) : ViewModel() {

    fun fetchMoviesCategories(){
        return moviesRepository.getAllCategories();
    }

    // TODO: Implement the ViewModel
}