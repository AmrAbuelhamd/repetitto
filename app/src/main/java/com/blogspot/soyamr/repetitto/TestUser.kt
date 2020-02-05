package com.blogspot.soyamr.repetitto

class TestUser constructor (var name: String,
                var email: String,
                var phone: String,
                var fac: String,
                var course: Int,
                var rate: Double,
                val id: Int)
{

    val skills: HashMap<String, Boolean> = hashMapOf(
            "Математика" to false,
            "История" to false,
            "Информатика" to false,
            "Химия" to false,
            "Дискретная математика" to false,
            "Психология" to false)

    @Override
    override fun toString(): String {
        return name
    }
}