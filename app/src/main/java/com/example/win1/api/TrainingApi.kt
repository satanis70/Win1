package com.example.win1.api

import com.example.win1.model.PostQueryModel
import com.example.win1.model.ResponseModel
import com.example.win1.model.TrainingModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TrainingApi {
    @GET("/ios/{day}.json")
    fun getTrainings(@Path("day") day:String):Call<TrainingModel>

    @POST("/ios/ask.php")
    fun setQuestion(@Body request: PostQueryModel):Call<Void>

    @POST("/ios/response.php")
    fun getResponse():Call<ResponseModel>
}