package com.example.win1

import android.content.Context
import android.widget.Toast

object Validator {
    fun validatePersonalData(name: String, height: String, weight: String, context: Context): Boolean {
        return if (name.isEmpty()){
            Toast.makeText(context, "Укажите имя", Toast.LENGTH_SHORT).show()
            false
        } else if(height.isEmpty()){
            Toast.makeText(context, "Укажите рост", Toast.LENGTH_SHORT).show()
            false
        } else if(weight.isEmpty()){
            Toast.makeText(context, "Укажите вес", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }
    fun validateAnalyticsData(distance: String, sitUps: String, context: Context): Boolean {
        return if (distance.isEmpty()) {
            Toast.makeText(context, "Укажите расстояние", Toast.LENGTH_SHORT).show()
            return false
        } else if (sitUps.isEmpty()) {
            Toast.makeText(context, "Укажите количество приседаний", Toast.LENGTH_SHORT).show()
            return false
        } else {
            true
        }
    }
}