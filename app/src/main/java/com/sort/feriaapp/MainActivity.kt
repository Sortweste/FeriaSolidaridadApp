package com.sort.feriaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sort.feriaapp.databinding.ActivityMainBinding
import com.sort.feriaapp.ui.GalleryFragment
import com.sort.feriaapp.ui.HomeFragment
import com.sort.feriaapp.ui.MediaFragment


class MainActivity : AppCompatActivity() {

    lateinit var mediaFragment: MediaFragment
    lateinit var galleryFragment: GalleryFragment
    lateinit var homeFragment: HomeFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeFragment = HomeFragment.newInstance("", "")
        galleryFragment = GalleryFragment.newInstance("", "")
        mediaFragment = MediaFragment.newInstance("", "")

        setCurrentFragment(homeFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.page_1 -> {
                    setCurrentFragment(homeFragment).addToBackStack(null)
                }
                R.id.page_2 -> {
                    setCurrentFragment(mediaFragment).addToBackStack(null)
                }
                R.id.page_3 -> {
                    setCurrentFragment(galleryFragment).addToBackStack(null)
                }
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment, fragment)
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
}