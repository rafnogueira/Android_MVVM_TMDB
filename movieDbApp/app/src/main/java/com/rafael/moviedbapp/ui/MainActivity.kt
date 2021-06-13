package com.rafael.moviedbapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rafael.moviedbapp.R
import com.rafael.moviedbapp.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_activity.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    lateinit var navController: NavController
    lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment.newInstance())
//                .commitNow()
//        }
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController() as NavController) ;

        navController = navHostFragment.navController

        //val appBarConfiguration = AppBarConfiguration(setOf(R.id.fragment_movies_catalog_home, R.id.fragment_movies_catalog_search, R.id.fragment_movies_catalog_favorites))

    //        bottomNavigationView.setupWithNavController(navController)


//        navController.navigate(R.id.action_moviesCatalogHome_to_movieDetails)

        //this.findNavController().navigate(R.layout.fragment_movie_details)
    }

}