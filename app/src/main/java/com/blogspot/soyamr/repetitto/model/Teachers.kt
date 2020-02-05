package com.blogspot.soyamr.repetitto.model


class Teachers {
    var name: String? = null
        private set
    var subject: String? = null
        private set
    var cost: Int = 0
        private set
    var id: Int = 0
        private set


    constructor(name: String, subject: String, cost: Int, id: Int) {
        this.name = name
        this.subject = subject
        this.cost = cost
        this.id = id
    }

}

//data class Teacher(val name: String?, val subject: String?, val cost: Int): Serializable
