package com.sort.feriaapp.data.repositories

import com.sort.feriaapp.data.User
import com.sort.feriaapp.data.dao.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao){

    suspend fun insertUser(user: User){
        userDao.insert(user)
    }

    fun getUser(username: String, password: String) = userDao.getUser(username, password)

}