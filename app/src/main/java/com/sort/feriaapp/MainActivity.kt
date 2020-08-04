package com.sort.feriaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sort.feriaapp.databinding.ActivityMainBinding
import com.sort.feriaapp.ui.GalleryFragment
import com.sort.feriaapp.ui.HomeFragment
import com.sort.feriaapp.ui.MediaFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topAppBar)

        binding.bottomNavigation.setupWithNavController(findNavController(R.id.fragment))

    }
}