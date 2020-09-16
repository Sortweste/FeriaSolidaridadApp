package com.sort.feriaapp.network.remotes

import com.sort.feriaapp.network.dtos.LoginDTO
import com.sort.feriaapp.network.dtos.UserDTO
import com.sort.feriaapp.network.services.UserService
import com.sort.feriaapp.utils.SafeApiRequest
import javax.inject.Inject

/*Class to perform User operations to API*/
class UserRemoteDataSource @Inject constructor(private val userService: UserService): SafeApiRequest() {

    suspend fun login(loginDTO: LoginDTO) = apiRequest { userService.login(loginDTO) }

    suspend fun register(userDTO: UserDTO) = apiRequest { userService.register(userDTO) }

    suspend fun subscribe(eventId: String, token: String) = apiRequest { userService.subscribe(eventId, token) }

    suspend fun unsubscribe(eventId: String, token: String) = apiRequest { userService.unsubscribe(eventId, token) }

    suspend fun getAttendEvent(token: String) = apiRequest { userService.getAttendEvent(token) }

}