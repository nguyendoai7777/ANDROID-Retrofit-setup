package com.example.pai.user

data class User(
    val createdAt: String,
    val name: String,
    val avatar: String,
    val id: String,
)

data class UserInfo(
    val firstName: String,
    val id: String,
    val lastName: String,
    val dob: String,
    val gender: String,
)