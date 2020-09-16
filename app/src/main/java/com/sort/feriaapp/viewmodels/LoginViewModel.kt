package com.sort.feriaapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sort.feriaapp.data.repositories.UserRepository
import com.sort.feriaapp.network.dtos.LoginDTO
import com.sort.feriaapp.utils.emailPattern

/*ViewModel for LoginActivity*/
class LoginViewModel @ViewModelInject constructor(private val repository: UserRepository): ViewModel(){

    /*Two-way databinding implementation*/
    var inputEmail = MutableLiveData<String?>()
    val inputPassword = MutableLiveData<String>()
    val errorEmail = MutableLiveData<String>()
    val errorPassword = MutableLiveData<String>()

    /*Send login credentials to API*/
    fun login() = repository.login(LoginDTO(inputEmail.value.toString(), inputPassword.value.toString()))

    /*Validate credentials*/
    fun performValidation(): Boolean{

        if(inputEmail.value.isNullOrBlank() || inputPassword.value.isNullOrBlank() || !inputEmail.value.toString().matches(emailPattern)){

            if(inputEmail.value.isNullOrBlank()) {
                errorEmail.value = "Ingrese correo electrónico"
            }else if(!inputEmail.value.toString().matches(emailPattern)) {
                errorEmail.value = "Ingrese un correo con extensión @uca.edu.sv"
            }else{
                errorEmail.value = null
            }

            if(inputPassword.value.isNullOrBlank()) {
                errorPassword.value = "Ingrese contraseña"
            }else{
                errorPassword.value = null
            }

            return false

        }else {
            errorEmail.value = null
            errorPassword.value = null
        }

        return true
    }

}