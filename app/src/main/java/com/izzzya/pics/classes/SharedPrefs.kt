package com.izzzya.pics.classes

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SharedPrefs(context: Context) {
    init {
        sharedPref = context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

    }
    companion object{
        private var sharedPref: SharedPreferences? = null
        const val PREFERENCES = "prefs"
        private val PICS: String = ""
        var LINK = ""


        fun setPicsList(list: List<String>){
            val json: String = Gson().toJson(list)
            set(PICS, json)
        }

        operator fun set(key: String?, value: String?) {
            val editor = sharedPref?.edit()
            editor?.putString(key, value)
            editor?.apply()
        }

        fun getPicsList(): List<String> {
            val arrayItems: List<String>
            val serializedObject: String? = sharedPref?.getString(PICS, "")
            return if (!serializedObject.isNullOrEmpty()) {
                val gson = Gson()
                val type: Type = object : TypeToken<List<String?>?>() {}.type
                arrayItems = gson.fromJson<List<String>>(serializedObject, type)
                arrayItems
            } else listOf<String>()
        }


    }
}