package com.rafael.movieapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rafael.movieapp.models.Genre


class MainViewModel : ViewModel() {
    var Categories: MutableLiveData<List<Genre>> = MutableLiveData<List<Genre>>()



}