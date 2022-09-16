package com.example.win1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_personal_data.*


class PersonalDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_data)
        val text = "<font color=#ffffff>WIN</font><font color=#F15400>SPORT</font>"
        textView_personal_win.text = Html.fromHtml(text)
        val context = this
        button_start.setOnClickListener {
            val name = editText_name.text.toString()
            val height = editText_height.text.toString()
            val weight = editText_weight.text.toString()
            if (Validator.validate(name, height, weight, this)){
                val sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
                sharedPreferences.edit {
                    putString("name", name)
                    putString("height", height)
                    putString("weight", weight)
                    commit()
                }
                startActivity(Intent(context, MenuActivity::class.java))
            }
        }
    }
}