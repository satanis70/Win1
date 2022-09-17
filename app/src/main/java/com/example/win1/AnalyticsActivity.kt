package com.example.win1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_analytics.*
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_training.*

class AnalyticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        val text = "<font color=#ffffff>WIN</font><font color=#F15400>SPORT</font>"
        textView_analytics_win.text = Html.fromHtml(text)
        val sharedPref = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        val weight = sharedPref.getString("weight", "")

        val sharedPrefProgress = applicationContext.getSharedPreferences("progressPref", Context.MODE_PRIVATE)
        var progressMemory = sharedPrefProgress.getString("progress", "")

        val text2 = "<font color=#ffffff>ПРОГРЕСС:</font><font color=#F15400>${progressMemory}Р</font>"
        textView_analytics_progress.text = Html.fromHtml(text2)

        button_input_data.setOnClickListener {
            val distance = editText_distance.text.toString()
            val sitUps = editText_sitUps.text.toString()
            if (Validator.validateAnalyticsData(distance, sitUps, this)){
                val progress = ((distance.toInt()+sitUps.toInt())*10*weight!!.toInt())/10
                Log.i("progress", progress.toString())
                progressMemory = (progressMemory!!.toInt()+progress).toString()
                Log.i("progressMemory", progressMemory.toString())
                val text3 = "<font color=#ffffff>ПРОГРЕСС:</font><font color=#F15400>${progressMemory}Р</font>"
                textView_analytics_progress.text = Html.fromHtml(text3)

                val sharedPreferences = getSharedPreferences("progressPref", Context.MODE_PRIVATE)
                sharedPreferences.edit {
                    putString("progress", progressMemory.toString())
                }
            }
        }


    }
}