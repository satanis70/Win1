package com.example.win1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.onesignal.OneSignal
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : AppCompatActivity() {

    var k = 0

    val ONESIGNAL_APP_ID = "714b9f14-381d-4fc4-a93c-28d480557381"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

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
        button_settings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
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
            progressBarMenu.max = 25000
            progressBarMenu.progress = progress.toInt()
            val textProgress = "<font color=#ffffff>ПРОГРЕСС:</font><font color=#F15400>${progress}</font><font color=#ffffff>Р</font>"
            text_view_progress.text = Html.fromHtml(textProgress)
        }
    }

    override fun onResume() {
        super.onResume()
        getProgress()
    }

    override fun onBackPressed() {
        k++
        if (k==1){
            Toast.makeText(this, "Нажмите кнопку назад еще раз что бы выйти", Toast.LENGTH_LONG).show();
        } else{
            finish()
        }
    }
}