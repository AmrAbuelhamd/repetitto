package com.blogspot.soyamr.repetitto.RetrofitPojo

data class User(
        val firstName: String? = null,
        val lastName: String,
        val patronymic: String,
        val price: Int,
        val avgMark: Double,
        val id: Int = -1
)
