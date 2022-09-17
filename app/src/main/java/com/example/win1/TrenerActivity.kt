package com.example.win1

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import com.example.win1.api.TrainingApi
import com.example.win1.model.PostQueryModel
import kotlinx.android.synthetic.main.activity_trener.*
import kotlinx.android.synthetic.main.activity_trener.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class TrenerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trener)

        val text = "<font color=#ffffff>WIN</font><font color=#F15400>SPORT</font>"
        textView_trener_win.text = Html.fromHtml(text)
        val sharedPrefQueryId = applicationContext.getSharedPreferences("queryPref", Context.MODE_PRIVATE)
        getData(sharedPrefQueryId)
        imageButton_send.setOnClickListener {
            sendData(sharedPrefQueryId)
        }
    }

    private fun getData(sharedPrefQueryId: SharedPreferences) {
        val id = sharedPrefQueryId.getString("uniqueID", "")
        val query = sharedPrefQueryId.getString("text", "")
        val textViewQuery = textView_query
        val textViewResp = textView_response
        if (!id.isNullOrEmpty()){
            val api = Retrofit.Builder()
                .baseUrl("http://84.38.181.162")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TrainingApi::class.java)

            GlobalScope.launch(Dispatchers.IO){
                val response = api.getResponse().awaitResponse()
                if (response.isSuccessful){
                    val data = response.body()
                    Log.i("data", data!!.response)
                    launch(Dispatchers.Main) {
                        delay(1000)
                        textViewQuery.text = query
                        textViewResp.text = data.response
                    }

                }
            }
        }

    }

    private fun sendData(sharedPrefQueryId: SharedPreferences) {
        val context = this
        val uniqueID = UUID.randomUUID().toString()
        val text = editText_question.text.toString()
        val api = Retrofit.Builder()
            .baseUrl("http://84.38.181.162")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TrainingApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.setQuestion(PostQueryModel(text, uniqueID)).awaitResponse()
            if (response.isSuccessful){
                val data = response.message()
                Log.i("post", data)
                sharedPrefQueryId.edit {
                    putString("uniqueID", uniqueID)
                    putString("text",text)
                }
                launch(Dispatchers.Main) {
                    textView_response.text = "Ответ обрабатывается"
                    Toast.makeText(context, "Сообщение отправлено!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}