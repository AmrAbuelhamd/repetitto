package com.blogspot.soyamr.repetitto.views;

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.soyamr.repetitto.R

class SearchFiltersActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var seekBar: SeekBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_filter)
        seekBar = findViewById<View>(R.id.seekBar) as SeekBar
        textView = findViewById<View>(R.id.txtView) as TextView
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })
        //todo save user input in object and create url with that object data and recieve data from server
        //according to it
    }


    fun onRadioButtonClicked(view: View) {}
    fun saveFilterChanges(view: View) {
        finish()
    }
}