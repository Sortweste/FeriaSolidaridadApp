package com.sort.feriaapp.data.repositories

import com.sort.feriaapp.data.User
import com.sort.feriaapp.data.dao.UserDao
import com.sort.feriaapp.utils.SafeApiRequest

class UserRepository(private val userDao: UserDao): SafeApiRequest() {

    suspend fun insertUser(user: User){
        userDao.insert(user)
    }

    fun getUser(username: String, password: String) = userDao.getUser(username, password)

    companion object {
        @Volatile private var instance: UserRepository? = null
        fun getInstance(userDao: UserDao) =
            instance ?: synchronized(this){
                instance ?: UserRepository(userDao).also { instance = it }
            }
    }

}