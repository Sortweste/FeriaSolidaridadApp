package com.sort.feriaapp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.sort.feriaapp.data.User
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao: BaseDao<User> {

    @Query("SELECT * FROM users WHERE email=:username and password=:password")
    abstract fun getUser(username: String, password: String): Flow<User>

}