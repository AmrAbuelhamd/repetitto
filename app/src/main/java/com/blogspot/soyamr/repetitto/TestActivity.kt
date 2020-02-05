package com.blogspot.soyamr.repetitto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity(){

    var users : Array<TestUser> = arrayOf(
            TestUser("Иван", "sadaf@", "12345", "физ", 2, 3.4, 1),
            TestUser("Петр", "sadaf@", "12345", "физ", 2, 3.2, 2),
            TestUser("Василий", "sadaf@", "12345", "физ", 2, 3.0, 3)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        users[0].skills.set("Математика", true)
        users[1].skills.set("Информатика", true)
        users[2].skills.set("Химия", true)

        button.setOnClickListener {
            val current = current.selectedItem as TestUser
            val target = target.selectedItem as TestUser
            val profileIntent = Intent(this, UserProfileActivity::class.java).apply {
                putExtra("CURRENT_USER", current.id)
                putExtra("TARGET_USER", target.id)
            }
            startActivity(profileIntent)
        }


        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, users)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        current.adapter = aa
        target.adapter = aa
    }
}
