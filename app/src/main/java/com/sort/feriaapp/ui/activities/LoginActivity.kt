package com.sort.feriaapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.sort.feriaapp.databinding.ActivityLoginBinding
import com.sort.feriaapp.storage.SharedPreferencesManager
import com.sort.feriaapp.utils.Resource
import com.sort.feriaapp.utils.TOKEN_KEY
import com.sort.feriaapp.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels ()

    @Inject
    lateinit var mSharedPreferences: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verifySession()
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

        initObservers()
    }

    private fun verifySession(){
        if(!mSharedPreferences.getData(TOKEN_KEY).isNullOrEmpty())
            startIntent()
    }

    private fun initObservers(){
        loginViewModel.login().observe(this, Observer {
            when(it.status){
               Resource.Status.SUCCESS -> {
                   binding.progressBar.visibility = View.GONE
                   Toast.makeText(this, it.data.toString(), Toast.LENGTH_LONG).show()
                   //mSharedPreferences.putData(TOKEN_KEY, it.data.toString())
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
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}