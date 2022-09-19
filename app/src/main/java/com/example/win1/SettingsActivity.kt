package com.example.win1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_trener.*
import kotlin.system.exitProcess

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val text = "<font color=#ffffff>WIN</font><font color=#F15400>SPORT</font>"
        textView_settings_win.text = Html.fromHtml(text)

        textView_clearData.setOnClickListener {
            Toast.makeText(this, "Данные очищены", Toast.LENGTH_SHORT).show()
            val sharedPref = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()
            val sharedPrefProgress = applicationContext.getSharedPreferences("progressPref", Context.MODE_PRIVATE)
            sharedPrefProgress.edit().clear().apply()
            startActivity(Intent(this, SplashActivity::class.java))
            finish()
        }
    }
}