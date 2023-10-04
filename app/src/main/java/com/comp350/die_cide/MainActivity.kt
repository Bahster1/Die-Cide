package com.comp350.die_cide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var msgTV: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val response = Response().response("What is the date today?", 6)
        msgTV = findViewById<TextView>(R.id.textView6)
        msgTV.text = response
    }
}
