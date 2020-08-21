package com.sort.feriaapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.ActivitySignUpBinding
import com.sort.feriaapp.viewmodels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnClickListener {

    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.signUpViewModel = signUpViewModel

        binding.buttonGoSignUp.setOnClickListener(this)
        binding.redirectLogin.setOnClickListener(this)

        initSpinner(binding.spinnerFaculty, R.array.facultades)

    }

    private fun initSpinner(v: Spinner, arrayResource: Int){
        ArrayAdapter.createFromResource(this, arrayResource, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
            v.adapter = adapter
        }
        v.onItemSelectedListener = this
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        when(parent?.id){
            binding.spinnerFaculty.id -> {  }
        }
    }

    private fun loginIntent(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.buttonGoSignUp.id -> {  }
            binding.redirectLogin.id -> { loginIntent() }
        }
    }

}