package com.blogspot.soyamr.repetitto.RetrofitPojo

data class LogInAnswer(
        val successful: Boolean? = null,
        val token: String? = null,
        val user: UserById? = null

)