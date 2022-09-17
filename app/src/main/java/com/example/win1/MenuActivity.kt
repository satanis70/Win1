package com.example.win1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.core.content.edit
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

        val sharedPref = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val name = sharedPref.getString("name", "")
        val height = sharedPref.getString("height", "")
        val weight = sharedPref.getString("weight", "")
        getProgress()

        button_training.setOnClickListener {
            startActivity(Intent(this, TrainingActivity::class.java))
        }
        button_analytics.setOnClickListener {
            startActivity(Intent(this, AnalyticsActivity::class.java))
        }
        button_trainer.setOnClickListener {
            startActivity(Intent(this, TrenerActivity::class.java))
        }

    }

    fun getProgress(){
        val sharedPrefProgress = applicationContext.getSharedPreferences("progressPref", Context.MODE_PRIVATE)
        val progress = sharedPrefProgress.getString("progress", "")
        if (progress!!.isEmpty()){
            sharedPrefProgress.edit {
                putString("progress", "0")
            }
            val textProgress = "<font color=#ffffff>ПРОГРЕСС:</font><font color=#F15400>0</font><font color=#ffffff>Р</font>"
            text_view_progress.text = Html.fromHtml(textProgress)
        } else{
            val textProgress = "<font color=#ffffff>ПРОГРЕСС:</font><font color=#F15400>${progress}</font><font color=#ffffff>Р</font>"
            text_view_progress.text = Html.fromHtml(textProgress)
        }
    }

    override fun onResume() {
        super.onResume()
        getProgress()
    }
}