package com.sort.feriaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sort.feriaapp.databinding.ActivityLoginBinding
import com.sort.feriaapp.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}