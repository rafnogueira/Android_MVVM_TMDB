package com.rafael.moviedbapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.room.Room
import androidx.room.RoomDatabase
import coil.load
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.data.datasource.AppDatabase

class MainFragment : Fragment() {
    companion object { fun newInstance() = MainFragment() }

    private lateinit var viewModel: MainViewModel

    private var db: RoomDatabase? = null

    var imageURLTest: String = "https://www.themoviedb.org/t/p/w1280/87aWrVqaVhXhblhO7sYHLC2y8TT.jpg"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var imageViewTest = view.findViewById<ImageView>(R.id.imageTest)

        imageViewTest?.load(imageURLTest) {
            crossfade(true)
            placeholder(R.drawable.selection_band_overlay)
        }


        initializeLocalDb()
    }

    private fun initializeLocalDb(){
        db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java, "fastshop-movies-db"
        ).build()



    }
}