package com.example.win1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_personal_data.*

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val text = "<font color=#ffffff>WIN</font><font color=#F15400>SPORT</font>"
        textView_menu_win.text = Html.fromHtml(text)
        val textGoal = "<font color=#ffffff>ЦЕЛЬ НА ДЕНЬ:</font>\n<font color=#F15400>25000</font><font color=#ffffff>POINTS</font>"
        text_view_goal.text = Html.fromHtml(textGoal)
        val textProgress = "<font color=#ffffff>ПРОГРЕСС:</font><font color=#F15400>15000</font><font color=#ffffff>Р</font>"
        text_view_progress.text = Html.fromHtml(textProgress)
        val sharedPref = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val name = sharedPref.getString("name", "")
        val height = sharedPref.getString("height", "")
        val weight = sharedPref.getString("weight", "")
        button_training.setOnClickListener {
            startActivity(Intent(this, TrainingActivity::class.java))
        }
    }
}