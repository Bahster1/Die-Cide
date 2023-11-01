/*
    * Copyright 2023 Bradley Walsh
 */
package com.comp350.die_cide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.comp350.die_cide.data.InteractionRoomDatabase

class HistoryActivity : AppCompatActivity() {
    private lateinit var db: InteractionRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_main)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        db = InteractionRoomDatabase.getDatabase(this)
//        val historyAdapter = HistoryAdapter(db.interactionDao().getInteractions())
//        val recyclerView: RecyclerView = findViewById(R.id.historyRecyclerView)
//        recyclerView.adapter = historyAdapter
    }

    // https://devofandroid.blogspot.com/2018/03/add-back-button-to-action-bar-android.html
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
