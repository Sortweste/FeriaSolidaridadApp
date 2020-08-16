package com.sort.feriaapp.data.repositories

import androidx.lifecycle.liveData
import com.sort.feriaapp.data.User
import com.sort.feriaapp.data.dao.UserDao
import com.sort.feriaapp.network.dtos.LoginDTO
import com.sort.feriaapp.network.remotes.UserRemoteDataSource
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao, private val userRemoteDataSource: UserRemoteDataSource){

    fun login(loginDTO: LoginDTO) = liveData{
        emitSource()
        //val user = userRemoteDataSource.login(loginDTO)
        //userDao.insert(user)
    }

    suspend fun insertUser(user: User){
        userDao.insert(user)
    }

    fun getUser(username: String, password: String) = userDao.getUser(username, password)

}