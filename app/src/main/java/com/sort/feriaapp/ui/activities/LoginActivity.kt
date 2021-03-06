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
import com.sort.feriaapp.network.responses.TokenResponse
import com.sort.feriaapp.storage.SharedPreferencesManager
import com.sort.feriaapp.utils.Resource
import com.sort.feriaapp.utils.TOKEN_KEY
import com.sort.feriaapp.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    /*Using databinding*/
    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    /*Injecting ViewModel*/
    private val loginViewModel: LoginViewModel by viewModels()

    /*Injecting SharedPreferences*/
    @Inject
    lateinit var mSharedPreferences: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //verifySession()
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

        binding.buttonGoLogin.setOnClickListener(this)
        binding.redirectSignUp.setOnClickListener(this)
    }

    private fun verifySession() {
        if (!mSharedPreferences.getData(TOKEN_KEY).isNullOrEmpty())
            startIntent()
    }

    private fun initObservers() {
        loginViewModel.login().observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    mSharedPreferences.putData(TOKEN_KEY, (it.data as TokenResponse).token)
                    startIntent()
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    binding.progressBar.visibility = View.GONE
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun startIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun registerIntent(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    /*Manage Log In and Register intents*/
    override fun onClick(v: View?) {
        when(v?.id){
            binding.buttonGoLogin.id -> {
                if (loginViewModel.performValidation())
                    lifecycleScope.launch {
                        initObservers()
                    }
            }
            binding.redirectSignUp.id -> { registerIntent() }
        }
    }

}