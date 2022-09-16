package com.example.win1

import android.content.Context
import android.widget.Toast

object Validator {
    fun validate(name: String, height: String, weight: String, context: Context): Boolean {
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
}