/*
 * Reference: https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#
 */
package com.comp350.die_cide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.comp350.die_cide.QuestionInput.Companion.getUserInput

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submitButton: Button = findViewById(R.id.submit)
        val questionField: EditText = findViewById(R.id.userQuestion)

        submitButton.setOnClickListener {
            val userQuestionInput = getUserInput(questionField)
            Toast.makeText(applicationContext, "Your question: $userQuestionInput", Toast.LENGTH_SHORT).show()
        }
    }
}
