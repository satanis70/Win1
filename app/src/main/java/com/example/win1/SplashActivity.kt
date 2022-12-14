package com.example.win1

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.android.synthetic.main.activity_splash.view.*


class SplashActivity : AppCompatActivity() {

    private var SPLASH_SCREEN_TIME: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val text = "<font color=#ffffff>WIN</font><font color=#F15400>SPORT</font>"
        textView_Splash.text = Html.fromHtml(text)
        val  context = this
        setAlarm()
        progressBarSplash.max = 3
        val timer = object: CountDownTimer(3000, 1000) {
            var currentProgress = 0
            override fun onTick(millisUntilFinished: Long) {
                currentProgress+=1
                progressBarSplash.progress = currentProgress
                Log.i("current", currentProgress.toString())
            }
            override fun onFinish() {
                val sharedPref = applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
                if (sharedPref.getString("name","")!!.isEmpty()){
                    val intent = Intent(context, PersonalDataActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(context, MenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        timer.start()

        setAlarm()
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun setAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ClearData::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, AlarmManager.INTERVAL_DAY, AlarmManager.INTERVAL_DAY, pendingIntent)
    }
}