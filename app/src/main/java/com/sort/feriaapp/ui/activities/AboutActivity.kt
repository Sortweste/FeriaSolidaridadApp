package com.sort.feriaapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sort.feriaapp.databinding.ActivityAboutBinding
import com.sort.feriaapp.viewmodels.AboutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AboutActivity : AppCompatActivity() {

    /*Using databinding*/
    private var _binding: ActivityAboutBinding? = null
    private val binding get() = _binding!!

    /*Injecting ViewModel*/
    private val aboutViewModel: AboutViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*Adding toolbar*/
        setSupportActionBar(binding.topAppBarAbout)
        binding.lifecycleOwner = this
        binding.viewmodel = aboutViewModel
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}