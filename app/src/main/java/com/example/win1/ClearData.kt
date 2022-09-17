package com.example.win1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.edit

class ClearData:BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val sharedPrefProgress = p0!!.getSharedPreferences("progressPref", Context.MODE_PRIVATE)
        val progress = sharedPrefProgress.getString("progress", "")
        if (!progress.isNullOrEmpty()){
            sharedPrefProgress.edit {
                putString("progress", "0")
            }
            Toast.makeText(p0, "Очищено", Toast.LENGTH_SHORT).show()
        }

    }
}