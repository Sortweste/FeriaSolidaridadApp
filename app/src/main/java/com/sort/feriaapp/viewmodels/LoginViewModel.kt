package com.sort.feriaapp.viewmodels

import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sort.feriaapp.data.repositories.UserRepository
import com.sort.feriaapp.network.dtos.LoginDTO


class LoginViewModel @ViewModelInject constructor(private val repository: UserRepository): ViewModel(){

    var inputEmail = MutableLiveData<String?>()
    val inputPassword = MutableLiveData<String>()
    val errorEmail = MutableLiveData<String>()
    val errorPassword = MutableLiveData<String>()

    fun login() = repository.login(LoginDTO(inputEmail.value.toString(), inputPassword.value.toString()))

    fun performValidation(): Boolean{
        if(inputEmail.value.isNullOrBlank()) {
            errorEmail.value = "Ingrese correo electrónico"
            return false
        }
        else{
            errorEmail.value = null
        }
        if(inputPassword.value.isNullOrBlank()) {
            errorPassword.value = "Ingrese contraseña"
            return false
        }
        else {
            errorPassword.value = null
        }
        //if(!inputEmail.value.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher().matches())
        return true
    }

}