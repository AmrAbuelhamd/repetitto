package com.blogspot.soyamr.repetitto.views;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.soyamr.repetitto.R
import com.blogspot.soyamr.repetitto.RetrofitPojo.FilterParameter
import kotlinx.android.synthetic.main.activity_search_filter.*

class SearchFiltersActivity : AppCompatActivity() {
    var course: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_filter)


    }


    fun saveFilterChanges(view: View) {
        FilterParameter.course = course
        FilterParameter.onlyTeacher = switch2.isChecked
        FilterParameter.faculty = editText3.text.toString()
        if (limitEditText.text.toString() != "")
            FilterParameter.limit = limitEditText.text.toString().toInt()
        FilterParameter.search = editText2.text.toString()

        Log.e("yes", FilterParameter.limit.toString())
        openRecyclerViewActivity()
    }

    private fun openRecyclerViewActivity() {
        val profileIntent = Intent(this, RecyclerViewActivity::class.java)
        startActivity(profileIntent)
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
}