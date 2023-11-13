/*
    * Bradley's ActionBar reference: https://developer.android.com/codelabs/android-room-with-a-view-kotlin#0
    * Copyright 2023 Bradley Walsh
 */
package com.comp350.die_cide

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comp350.die_cide.data.Interaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clear -> historyViewModel.deleteAll()
        }

        return super.onOptionsItemSelected(item)
    }
}
