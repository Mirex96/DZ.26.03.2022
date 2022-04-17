package com.example.dz26032022

import android.content.Context
import android.content.SharedPreferences



object ProductPreferences {
    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
    }

    var name: String
        get() {
           return sharedPreferences.getString(KEY_SP_PRODUCT, "") ?: ""
        }
        set(value) {
            sharedPreferences.edit()
                .putString(KEY_SP_PRODUCT, value)
                .apply()
        }
}