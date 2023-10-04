package com.comp350.die_cide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.comp350.die_cide.QuestionInput.Companion.getUserInput

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.submit.setOnClickListener {
            var userQuestionInput = getUserInput(findViewById(com.google.android.material.R.id.search_view_edit_text))
            Toast.makeText(applicationContext, "Your question: $userQuestionInput", Toast.LENGTH_SHORT).show()
        }
    }
}
