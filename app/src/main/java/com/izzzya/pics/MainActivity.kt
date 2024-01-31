package com.izzzya.pics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.izzzya.pics.classes.DataSource
import com.izzzya.pics.classes.SharedPrefs

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPrefs(this)
        setContentView(R.layout.activity_main)
    }
}