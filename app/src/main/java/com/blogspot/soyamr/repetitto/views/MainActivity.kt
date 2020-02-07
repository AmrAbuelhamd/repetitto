package com.blogspot.soyamr.repetitto.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.soyamr.repetitto.Constants
import com.blogspot.soyamr.repetitto.R
import com.blogspot.soyamr.repetitto.RetrofitPojo.LogInAnswer
import com.blogspot.soyamr.repetitto.RetrofitPojo.LogInRequest
import com.blogspot.soyamr.repetitto.RetrofitPojo.UserById
import com.blogspot.soyamr.repetitto.RetrofitSingleton
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()
        checkUserId()
    }

    private fun checkUserId() {
        val sharedPreference = getSharedPreferences(Constants.prefName, Context.MODE_PRIVATE)
        val userId: Int? = sharedPreference.getInt(Constants.userId, -1)
        //Log.v("checkid","id: "+userId)

        if (userId != -1) {/*user is registered go to second screen*/
            openRecyclerViewActivity()
        }//in case there's no id for him then do nothing

    }

    override fun onBackPressed() {
        finish();
        moveTaskToBack(true)

    }

    fun openSignUpActivity(view: View) {
        val profileIntent = Intent(this, SignUpActivity::class.java)
        startActivity(profileIntent)
    }

    fun showListTeachers(view: View) {
        var email: String = email_editText.text.toString()
        var passowrd: String = passord_edit_text.text.toString()
        if (email.isEmpty()) {
            email_editText.setError("please write something")
            return
        }
        if (passowrd.isEmpty()) {
            passord_edit_text.setError("please write something")
            return
        }

        RetrofitSingleton.retrofitObject.getLogInData(LogInRequest(email, passowrd))
                .enqueue(object : retrofit2.Callback<LogInAnswer> {
                    override fun onFailure(call: Call<LogInAnswer>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "something wentWrong", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<LogInAnswer>, response: Response<LogInAnswer>) {
                        val loginAnswer: LogInAnswer? = response.body()
                        if (loginAnswer?.successful!!) {
                            val user: UserById? = loginAnswer.user;
                            storeUserIdAndTokenInSharedPref(user?.id, loginAnswer.token)
                            openRecyclerViewActivity()
                        } else
                            showWrongPasswordOrEmail()
                    }
                })

        // openRecyclerViewActivity()
    }

    private fun storeUserIdAndTokenInSharedPref(id: Int?, token: String?) {
        val sharedPreference = getSharedPreferences(Constants.prefName, Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putInt(Constants.userId, id!!)
        editor.putString(Constants.token, token)
        editor.apply()
    }

    private fun showWrongPasswordOrEmail() {
        Toast.makeText(this@MainActivity, "wrong email or password", Toast.LENGTH_LONG).show()
        hideKeyboard()
    }

    private fun hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun openRecyclerViewActivity() {
        val profileIntent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(profileIntent)
    }


}