package com.sort.feriaapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.ActivitySignUpBinding
import com.sort.feriaapp.network.responses.TokenResponse
import com.sort.feriaapp.storage.SharedPreferencesManager
import com.sort.feriaapp.utils.Resource
import com.sort.feriaapp.utils.TOKEN_KEY
import com.sort.feriaapp.viewmodels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity(), View.OnClickListener {

    /*Using databinding*/
    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding!!

    /*Injecting ViewModel*/
    private val signUpViewModel: SignUpViewModel by viewModels()

    /*Career Spinner*/
    private lateinit var careerAdapter: ArrayAdapter<CharSequence>

    /*Injecting SharedPreferences*/
    @Inject
    lateinit var mSharedPreferences: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.signUpViewModel = signUpViewModel

        binding.buttonGoSignUp.setOnClickListener(this)
        binding.redirectLogin.setOnClickListener(this)

        binding.spinnerFaculty.adapter = spinnerAdapter(R.array.facultades)
        binding.spinnerYear.adapter = spinnerAdapter(R.array.year)

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

    /*Observe when Spinner Career Values Change*/
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

    private fun initObservers(){
        signUpViewModel.register().observe(this, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBarSignUp.visibility = View.GONE
                    mSharedPreferences.putData(TOKEN_KEY, (it.data as TokenResponse).token)
                    startIntent()
                }
                Resource.Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    binding.progressBarSignUp.visibility = View.GONE
                }
                Resource.Status.LOADING -> {
                    binding.progressBarSignUp.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun startIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loginIntent(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            binding.buttonGoSignUp.id -> {
                if(signUpViewModel.performValidation())
                    lifecycleScope.launch {
                        initObservers()
                    }
            }
            binding.redirectLogin.id -> { loginIntent() }
        }
    }

}