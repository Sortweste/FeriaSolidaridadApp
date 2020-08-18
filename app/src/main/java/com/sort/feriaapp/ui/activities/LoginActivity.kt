package com.sort.feriaapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.sort.feriaapp.databinding.ActivityLoginBinding
import com.sort.feriaapp.utils.Resource
import com.sort.feriaapp.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

        initObservers()
    }

    private fun initObservers(){
        loginViewModel.login().observe(this, Observer {
            when(it.status){
               Resource.Status.SUCCESS -> {
                   binding.progressBar.visibility = View.GONE
                   startIntent()
               }
                Resource.Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
                Resource.Status.LOADING -> {binding.progressBar.visibility = View.VISIBLE}
            }
        })
    }

    private fun startIntent(){

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}