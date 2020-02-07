package com.blogspot.soyamr.repetitto.RetrofitPojo

class CreateUser(
        var email: String? = null,
        var password: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var patronymic: String? = null,
        var faculty: String? = null,
        var course: Int? = null,

        var subjects: Array<Int>? = null,
        var degree: String? = null,
        var about: String? = null,
        var isTeacher: Boolean? = null,
        var price: Int? = null
)