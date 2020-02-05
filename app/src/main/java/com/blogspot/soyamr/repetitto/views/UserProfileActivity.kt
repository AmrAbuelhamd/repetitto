package com.blogspot.soyamr.repetitto.views

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.blogspot.soyamr.repetitto.R
import com.google.android.material.chip.Chip

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }


        val currentId = intent.extras?.get("teacherId") as Int

        findViewById<TextView>(R.id.textView12).text = currentId.toString()
        //make retrofit call and recieve his data to show it to the user

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
