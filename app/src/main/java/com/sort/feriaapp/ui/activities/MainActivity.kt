package com.sort.feriaapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.ActivityMainBinding
import com.sort.feriaapp.storage.SharedPreferencesManager
import com.sort.feriaapp.utils.TOKEN_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    //private var menu: Menu? = null

    @Inject
    lateinit var mSharedPreferences: SharedPreferencesManager

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
            R.id.homeFragment, R.id.profileFragment, R.id.mediaFragment, R.id.newsFragment -> componentsVisibility(View.VISIBLE)
            else -> componentsVisibility(View.GONE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.app_bar_menu, menu)
        /*if (!mSharedPreferences.getData(TOKEN_KEY).isNullOrEmpty()) {
            menu?.findItem(R.id.log_out)?.isVisible = true
            menu?.findItem(R.id.login_in)?.isVisible = false
        }
        this.menu = menu*/
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
          /*  R.id.log_out -> {
                mSharedPreferences.clearData(TOKEN_KEY)
                this.menu?.findItem(R.id.log_out)?.isVisible = false
                invalidateOptionsMenu()
                true
            }
            R.id.login_in -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                true
            }*/
            else -> super.onOptionsItemSelected(item)
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