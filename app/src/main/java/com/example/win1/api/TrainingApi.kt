package com.example.win1.api

import com.example.win1.model.TrainingModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TrainingApi {
    @GET("/ios/{day}.json")
    fun getTrainings(@Path("day") day:String):Call<TrainingModel>
}