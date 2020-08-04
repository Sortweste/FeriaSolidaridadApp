package com.sort.feriaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sort.feriaapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)

        val navController = findNavController(R.id.fragment)
        binding.bottomNavigation.setupWithNavController(navController)
        navController.addOnDestinationChangedListener(this)
    }

    override fun onDestinationChanged(controller: NavController, destination: NavDestination, arguments: Bundle?) {
        when(destination.id){
            R.id.homeFragment, R.id.galleryFragment, R.id.mediaFragment -> componentsVisibility(View.VISIBLE)
            else -> componentsVisibility(View.GONE)
        }
    }

    private fun componentsVisibility(status: Int){
        binding.bottomNavigation.visibility = status
        binding.topAppBar.visibility = status
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}