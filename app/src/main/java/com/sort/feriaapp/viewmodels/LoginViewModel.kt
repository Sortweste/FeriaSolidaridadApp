package com.sort.feriaapp.viewmodels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.sort.feriaapp.data.repositories.UserRepository

class LoginViewModel(private val repository: UserRepository): ViewModel(){

    var inputEmail: String = ""

    val inputPassword: String = ""


    /*private val _getUserInfo: LiveData<User> = userRepository.getUser().asLiveData()

val institutionInfo: LiveData<User>
get() = _getUserInfo*/


    private val _emailEditTextContent = 1


    fun login(){

    }

    private fun performValidation(){

    }

}