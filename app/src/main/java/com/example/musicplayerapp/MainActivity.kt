package com.example.musicplayerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.musicplayerapp.databinding.ActivityMainBinding
import com.example.musicplayerapp.di.MusicComponent

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        MusicApp.musicComponent.inject(this)

        val bottomNavigation = binding.musicNavigationBar

        val navController = findNavController(R.id.nav_container)

        val toolbarNav = AppBarConfiguration(navController.graph)

        setupActionBarWithNavController(navController,toolbarNav)

        bottomNavigation.setupWithNavController(navController)
    }
}