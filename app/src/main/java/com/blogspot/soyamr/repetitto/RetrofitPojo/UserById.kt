package com.blogspot.soyamr.repetitto.RetrofitPojo


data class UserById(
        val name: String? = null,
        val email: String,
        val course: Int,
        val price: Int? = null,
        val firstName: String,
        val lastName: String,
        val patronymic: String,
        val faculty: String,
        val subjects: Array<String>,
        val degree: String,
        val about: String,
        val isTeacher: Boolean,
        val id: Int = -1
)

//data class Teacher(val name: String?, val subject: String?, val cost: Int): Serializable
