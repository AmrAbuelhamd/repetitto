package com.blogspot.soyamr.repetitto

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


        val currentId = intent.extras?.get("CURRENT_USER") as Int
        val targetId = intent.extras?.get("TARGET_USER") as Int

        val user: TestUser = TestActivity().users[targetId]



        changeChipsVisibility(findViewById(R.id.Математика))
        changeChipsVisibility(findViewById(R.id.История))
        changeChipsVisibility(findViewById(R.id.Информатика))
        changeChipsVisibility(findViewById(R.id.Химия))
        changeChipsVisibility(findViewById(R.id.Дискретная_математика))
        changeChipsVisibility(findViewById(R.id.Психология))
    }

    fun changeChipsVisibility(v: View){
        v as Chip
        /*if (v.isChecked)
            v.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent))
        else
            v.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))*/
        if (v.isChecked)
            v.visibility = View.VISIBLE
        else
            v.visibility = View.GONE
    }
}
