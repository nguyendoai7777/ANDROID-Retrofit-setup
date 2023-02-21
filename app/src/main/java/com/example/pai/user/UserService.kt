package com.example.pai.user

import com.example.pai.configurations.Constant.Companion.ALL_USERS
import retrofit2.http.GET

interface UserService {
    @GET(ALL_USERS)
    suspend fun getAllUsers(): List<User>
}