package com.rafael.moviedbapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.room.Room
import androidx.room.RoomDatabase
import coil.load
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.data.dao.FavoriteMoviesDao
import com.rafael.moviedbapp.data.datasource.AppDatabase
import com.rafael.moviedbapp.data.models.FavoriteMovie
import com.rafael.moviedbapp.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var imageViewTest = view.findViewById<ImageView>(R.id.imageTest)

        var insertDbBtn = view.findViewById<Button>(R.id.insertDb).setOnClickListener {
            insertDbTest()
        }


        var recoverDbBtn = view.findViewById<Button>(R.id.recoverDb).setOnClickListener{
            getDbValues()
        }


        imageViewTest?.load(imageURLTest) {
            crossfade(true)
            placeholder(R.drawable.selection_band_overlay)
        }

        var insertDb = view.findViewById<Button>(R.id.insertDb)

    }

    private fun insertDbTest(){
        val movieTest = FavoriteMovie(
            0,
            1,
            "Movie Test",
            "",
            "",
            1,
            "",
            ""
        );
    }

    private fun getDbValues(){
        val result = viewModel.fetchMoviesCategories();
        val test =  0;
    }

}