package com.blogspot.soyamr.repetitto.views;

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.blogspot.soyamr.repetitto.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun showListTeachers(view: View) {
        //read the user data and send it to the server and recieve the user id and save it in sharedpreferences variable
        //also receive his data and save it in abstract static class
        val profileIntent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(profileIntent)
    }

    fun showOtherPrefs(view: View) {
        val constraintLayout: ConstraintLayout = this.findViewById(R.id.constraintLayout)
        val switch: Switch = this.findViewById(R.id.switch1)

        if (switch.isChecked)
            constraintLayout.visibility = View.VISIBLE
        else
            constraintLayout.visibility = View.GONE

    }

}
