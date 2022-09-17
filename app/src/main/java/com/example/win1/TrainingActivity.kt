package com.example.win1

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.ViewGroupCompat
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import com.example.win1.api.TrainingApi
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_training.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.util.*

class TrainingActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
        val text = "<font color=#ffffff>WIN</font><font color=#F15400>SPORT</font>"
        textView_training_win.text = Html.fromHtml(text)
        val day = getCurrentDay()
        val layoutParamsImage = imageView_training.layoutParams as ConstraintLayout.LayoutParams
        val layoutParamsTv = textView_training.layoutParams as ConstraintLayout.LayoutParams
        getData()
        button_training_dropdown.setOnClickListener {
            textView_training.maxLines = 500
            layoutParamsImage.setMargins(0, 50, 0, 0)
            imageView_training.layoutParams = layoutParamsImage
            layoutParamsTv.setMargins(0,50,0,0)
            textView_training.layoutParams = layoutParamsTv
            nestedScrollView.layoutParams = ViewGroup
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)
            textView_training_win.visibility = View.INVISIBLE
        }
    }

    private fun getData() {
        val context = this
        val api = Retrofit.Builder()
            .baseUrl("http://84.38.181.162")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrainingApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getTrainings(getCurrentDay()).awaitResponse()
            if (response.isSuccessful){
                val data = response.body()!!
                //Toast.makeText(context, data.toString(), Toast.LENGTH_SHORT).show()
                Log.i("response", data.toString())
            }
        }
    }

    fun getCurrentDay(): String {
        val calendar = Calendar.getInstance()
        val day = calendar[Calendar.DAY_OF_WEEK]-1
        when (day) {
            0 -> return "sunday"
            1 -> return "monday"
            2 -> return "tuesday"
            3 -> return "wednesday"
            4 -> return "thursday"
            5 -> return "friday"
            6 -> return "saturday"
        }
        return "Worng Day"
    }
}