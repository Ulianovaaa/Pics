package com.izzzya.pics.classes

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class DataSource {

    companion object{
        val source = "https://files.apkcdn.com/images.txt"
        var links = mutableListOf<String>()
        fun getImageLinks(){
            Thread {
                try {
                    val url = URL(source)
                    val uc: HttpsURLConnection = url.openConnection() as HttpsURLConnection
                    val br = BufferedReader(InputStreamReader(uc.getInputStream()))
                    var line: String?
                    val lin2 = mutableListOf<String>()
                    while (br.readLine().also { line = it } != null) {
                        line?.let { lin2.add(it) }
//                        Log.i("LINE ADDED", line!!)
                    }
                    links = lin2
                    SharedPrefs.setPicsList(lin2)
                    Log.d("The Text", lin2.toString())
                } catch (e: IOException) {
                    Log.d("texts", "onClick: " + e.getLocalizedMessage())
                    e.printStackTrace()
                }
            }.start()

        }


    }

}