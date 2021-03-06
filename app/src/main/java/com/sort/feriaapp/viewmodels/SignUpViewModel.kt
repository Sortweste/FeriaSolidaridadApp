package com.sort.feriaapp.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sort.feriaapp.data.repositories.UserRepository
import com.sort.feriaapp.helpers.UserHelper
import com.sort.feriaapp.network.dtos.UserDTO
import com.sort.feriaapp.utils.emailPattern

/*ViewModel for SignUpActivity*/
class SignUpViewModel @ViewModelInject constructor(private val userRepository: UserRepository): ViewModel() {

    /*Two-way databinding*/
    var inputEmail = MutableLiveData<String?>()
    val inputPassword = MutableLiveData<String>()
    val inputFirstName = MutableLiveData<String>()
    val inputLastName = MutableLiveData<String>()

    val faculty = MutableLiveData<String>()
    val career = MutableLiveData<String>()
    val year = MutableLiveData<String>()

    val errorEmail = MutableLiveData<String>()
    val errorPassword = MutableLiveData<String>()
    val errorFirstName = MutableLiveData<String>()
    val errorLastName = MutableLiveData<String>()


    /*Send register credentials to API*/
    fun register() = userRepository.register(UserDTO(UserHelper(career.value.toString(), inputEmail.value.toString(), inputFirstName.value.toString(), inputLastName.value.toString(), inputPassword.value.toString(), year.value?.get(0).toString().toInt())))

    /*Validates credentials*/
    fun performValidation(): Boolean{

        if(inputEmail.value.isNullOrBlank() || inputPassword.value.isNullOrBlank() || !inputEmail.value.toString().matches(emailPattern) ||
            inputFirstName.value.isNullOrBlank() || inputLastName.value.isNullOrBlank() ){

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

            if(inputFirstName.value.isNullOrBlank()){
                errorFirstName.value = "Ingrese un nombre"
            }else{
                errorFirstName.value = null
            }

            if(inputLastName.value.isNullOrBlank()){
                errorLastName.value = "Ingrese un apellido"
            }else{
                errorLastName.value = null
            }

            return false

        }else {
            errorEmail.value = null
            errorPassword.value = null
            errorFirstName.value = null
            errorLastName.value = null
        }

        return true
    }

}