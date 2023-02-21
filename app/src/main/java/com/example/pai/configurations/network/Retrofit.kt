package com.example.pai.configurations.network

import com.example.pai.configurations.Constant.Companion.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    var retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    inline fun <reified T> getService(): T = retrofit.create(T::class.java)
}

