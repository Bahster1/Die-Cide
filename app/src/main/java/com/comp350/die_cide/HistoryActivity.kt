/*
    * Bradley's ActionBar reference: https://developer.android.com/codelabs/android-room-with-a-view-kotlin#0
    * Copyright 2023 Bradley Walsh
 */
package com.comp350.die_cide

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.comp350.die_cide.viewmodels.HistoryViewModel
import com.comp350.die_cide.viewmodels.HistoryViewModelFactory

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
        title = "History"
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clear -> clear()
        }

        return super.onOptionsItemSelected(item)
    }

    // https://www.tutorialkart.com/kotlin-android/android-alert-dialog-example/#gsc.tab=0
    private fun clear() {
        val dialogBuilder = AlertDialog.Builder(this)

        dialogBuilder.setMessage("Are you sure you want to clear your history?")
            .setCancelable(true)
            .setPositiveButton("Yes") { _, _ ->
                historyViewModel.deleteAll()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }

        val alert = dialogBuilder.create()

        alert.setTitle("Warning")
        alert.show()
    }
}
