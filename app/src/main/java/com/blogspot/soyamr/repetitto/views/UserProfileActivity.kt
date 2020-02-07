package com.blogspot.soyamr.repetitto.views

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.blogspot.soyamr.repetitto.Constants
import com.blogspot.soyamr.repetitto.R
import com.blogspot.soyamr.repetitto.RetrofitPojo.UserById
import com.blogspot.soyamr.repetitto.RetrofitSingleton
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_user_profile.*
import retrofit2.Call
import retrofit2.Response

class UserProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }


        //findViewById<TextView>(R.id.textView12).text = currentId.toString()
        //make retrofit call and recieve his data to show it to the user

    }

    override fun onResume() {
        super.onResume()
        fillUserDataFromServer()

    }

    private fun fillUserDataFromServer() {
        val sharedPreference = getSharedPreferences(Constants.prefName, Context.MODE_PRIVATE)
        val userId = intent.extras?.get("teacherId") as Int
        val userToken: String? = sharedPreference.getString(Constants.token, "null")
        RetrofitSingleton.retrofitObject
                .getCurrentUser(userId.toString(), userToken).enqueue(object : retrofit2.Callback<UserById> {
                    override fun onFailure(call: Call<UserById>, t: Throwable) {
                        Toast.makeText(this@UserProfileActivity, "something wentWrong", Toast.LENGTH_LONG).show()
                        return
                    }

                    override fun onResponse(call: Call<UserById>, response: Response<UserById>) {
                        var userById: UserById? = response.body()
                        fillThistUserData(userById)
                    }
                })
    }

    private fun fillThistUserData(userById: UserById?) {
        name.text = userById?.firstName
        email_editText.text = userById?.email

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
