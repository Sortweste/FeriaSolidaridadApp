package com.sort.feriaapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sort.feriaapp.data.repositories.UserRepository
import com.sort.feriaapp.network.dtos.UserDTO
import com.sort.feriaapp.utils.emailPattern

class SignUpViewModel @ViewModelInject constructor(private val userRepository: UserRepository): ViewModel() {

    var inputEmail = MutableLiveData<String?>()
    val inputPassword = MutableLiveData<String>()
    val inputFirstName = MutableLiveData<String>()
    val inputLastName = MutableLiveData<String>()
    val faculty = MutableLiveData<String>()

    val errorEmail = MutableLiveData<String>()
    val errorPassword = MutableLiveData<String>()
    val errorFirstName = MutableLiveData<String>()
    val errorLastName = MutableLiveData<String>()


    //fun register() = userRepository.register(UserDTO())

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