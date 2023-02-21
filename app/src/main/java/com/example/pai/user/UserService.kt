package com.example.pai.user

import com.example.pai.configurations.Constant.Companion.ALL_USERS
import com.example.pai.configurations.Constant.Companion.USER_INFO
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET(ALL_USERS)
    suspend fun getAllUsers(): List<User>

    @GET("$USER_INFO/{id}")
    suspend fun getUserSettings(@Path("id") id: String): UserInfo
}

