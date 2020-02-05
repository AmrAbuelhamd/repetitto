package com.blogspot.soyamr.repetitto.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.soyamr.repetitto.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //id is sharedpreferences variable that indicate that the user has an id, i will use it to
        //check signing in
        //if id not null then make retrofit call to get user data from server to fill his profile
        // and go directly to show recyclerview activity
    }

    fun openSignInActivity(view: View) {
        val profileIntent = Intent(this, SignUpActivity::class.java)
        startActivity(profileIntent)
    }

    fun showListTeachers(view: View) {
        val profileIntent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(profileIntent)
    }

}