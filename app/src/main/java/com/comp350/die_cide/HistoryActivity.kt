/*
    * Bradley's ActionBar reference: https://developer.android.com/codelabs/android-room-with-a-view-kotlin#0
    * Copyright 2023 Bradley Walsh
 */
package com.comp350.die_cide

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comp350.die_cide.data.InteractionRoomDatabase

class HistoryActivity : AppCompatActivity() {
    private val historyViewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory((application as DieCideApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val recyclerView: RecyclerView = findViewById(R.id.historyRecyclerView)
        val adapter = HistoryAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        historyViewModel.allInteractions.observe(this) { interactions ->
            interactions.let { adapter.submitList(it) }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
