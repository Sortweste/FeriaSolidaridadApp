package com.sort.feriaapp.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sort.feriaapp.data.User
import com.sort.feriaapp.data.dao.UserDao
import com.sort.feriaapp.network.dtos.LoginDTO
import com.sort.feriaapp.network.dtos.UserDTO
import com.sort.feriaapp.network.remotes.UserRemoteDataSource
import com.sort.feriaapp.network.responses.TokenResponse
import com.sort.feriaapp.utils.Resource
import com.sort.feriaapp.utils.performGetOperation
import com.sort.feriaapp.utils.performPostOperation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao, private val userRemoteDataSource: UserRemoteDataSource){

    fun login(loginDTO: LoginDTO) = performPostOperation{ userRemoteDataSource.login(loginDTO) }

    fun register(userDTO: UserDTO) = performPostOperation { userRemoteDataSource.register(userDTO) }

    fun subscribe(eventId: String, token:String) = performPostOperation { userRemoteDataSource.subscribe(eventId, token) }

    //fun getAttendEvent(token: String) = performGetOperation(networkCall = {userRemoteDataSource.getAttendEvent(token)}, saveCallResult = {Unit})

    suspend fun insertUser(user: User){
        userDao.insert(user)
    }

    fun getUser(username: String, password: String) = userDao.getUser(username, password)

}