package com.example.notbored.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    val instance: Retrofit
        get() {
            return Retrofit
                .Builder()
                .baseUrl("https://www.boredapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

}