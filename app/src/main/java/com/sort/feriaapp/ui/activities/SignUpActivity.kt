package com.sort.feriaapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.ActivitySignUpBinding
import com.sort.feriaapp.viewmodels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!

    private val signUpViewModel: SignUpViewModel by viewModels()

    private lateinit var careerAdapter: ArrayAdapter<CharSequence>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.signUpViewModel = signUpViewModel

        binding.buttonGoSignUp.setOnClickListener(this)
        binding.redirectLogin.setOnClickListener(this)

        binding.spinnerFaculty.adapter = spinnerAdapter(R.array.facultades)
        initCareerSpinner(R.array.economicas_y_empresariales)
        careerSpinnerObserver()
    }

    private fun spinnerAdapter(arrayResource: Int) = ArrayAdapter.createFromResource(this, arrayResource, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice)
        }


    private fun initCareerSpinner(resource: Int){
        careerAdapter = spinnerAdapter(resource)
        binding.spinnerCareer.adapter = careerAdapter
        careerAdapter.notifyDataSetChanged()
    }

    private fun careerSpinnerObserver(){
        signUpViewModel.faculty.observe(this, Observer {
            when(it){
                "Ciencias Económicas y Empresariales" -> {modifyCareerAdapter(R.array.economicas_y_empresariales)}
                "Ciencias Sociales y Humanidades" -> {modifyCareerAdapter(R.array.sociales_y_humanidades)}
                "Ingeniería y Arquitectura" -> {modifyCareerAdapter(R.array.arquitectura_e_ingenieria)}
            }
        })
    }

    private fun modifyCareerAdapter(resource: Int) = initCareerSpinner(resource)

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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