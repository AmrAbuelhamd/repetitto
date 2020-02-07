package com.blogspot.soyamr.repetitto.views;

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.blogspot.soyamr.repetitto.Constants
import com.blogspot.soyamr.repetitto.R
import com.blogspot.soyamr.repetitto.RetrofitPojo.CreateUser
import com.blogspot.soyamr.repetitto.RetrofitPojo.UserById
import com.blogspot.soyamr.repetitto.RetrofitSingleton
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.teacher_data.*
import retrofit2.Call
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    var course: Int? = null
    var subjects: Array<Int>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
    }

    fun createUser(view: View) {
        if (first_name.text.toString().isEmpty() || passord_edit_text.text.toString().isEmpty() || email_editText.text.isEmpty()) {
            Toast.makeText(this, "fill the required fields please", Toast.LENGTH_LONG).show()
            return
        }
        val userData = CreateUser(email_editText.text.toString(), passord_edit_text.text.toString(), first_name.text.toString(), last_name_edit_text.text.toString()
                , null, faculty.text.toString(), course, subjects, degreeditText.text.toString()
                , abouteditText.text.toString(), switch1.isChecked, priceeditText.text.toString().toInt())

        RetrofitSingleton.retrofitObject.createUser(userData)
                .enqueue(object : retrofit2.Callback<UserById> {
                    override fun onFailure(call: Call<UserById>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, "something wentWrong", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<UserById>, response: Response<UserById>) {
//                        val user: UserById? = response.body()
//                        Log.v("amor", "email" + user?.email + " firstname: "
//                                + user?.firstName + " id: ")
                        openSignInActivity()
                    }
                })

    }

    private fun openSignInActivity() {
        Toast.makeText(this@SignUpActivity, "logIn Please", Toast.LENGTH_LONG).show()
        val sharedPreference = getSharedPreferences(Constants.prefName, Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putInt(Constants.userId, -1)
        editor.apply()

        val profileIntent = Intent(this, MainActivity::class.java)
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

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radioButton1 ->
                    if (checked) {
                        course = 1
                    }
                R.id.radioButton2 ->
                    if (checked) {
                        course = 2
                    }
                R.id.radioButton3 ->
                    if (checked) {
                        course = 3
                    }
                R.id.radioButton4 ->
                    if (checked) {
                        course = 4
                    }
                R.id.radioButton5 ->
                    if (checked) {
                        course = 5
                    }
                R.id.radioButton6 ->
                    if (checked) {
                        course = 6
                    }
            }
        }
    }

    fun onCheckboxClicked(view: View) {
        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.checkBox -> {
                    if (checked) {

                    } else {
                        // Remove the meat
                    }
                }
                R.id.checkBox -> {
                    if (checked) {
                        // Put some meat on the sandwich
                    } else {
                        // Remove the meat
                    }
                }
            }
        }
    }

}
