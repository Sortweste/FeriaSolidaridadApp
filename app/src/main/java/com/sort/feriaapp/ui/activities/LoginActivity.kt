package com.sort.feriaapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.sort.feriaapp.databinding.ActivityLoginBinding
import com.sort.feriaapp.utils.InjectorUtils
import com.sort.feriaapp.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels {
        InjectorUtils.provideLoginViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.loginViewModel = loginViewModel

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}