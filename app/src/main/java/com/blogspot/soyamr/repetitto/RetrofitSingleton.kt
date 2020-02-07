package com.blogspot.soyamr.repetitto

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitSingleton : Application() {
    companion object {
        private val retrofit =
                Retrofit.Builder()
                        .baseUrl("https://repetito.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
        val retrofitObject = retrofit.create(RetroFitApi::class.java)
    }

    override fun onCreate() {
        super.onCreate()

    }
}