package com.comp350.die_cide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.comp350.die_cide.QuestionInput.Companion.getUserInput
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var openAIResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openAIResponse = findViewById(R.id.textView6)

        val submitButton: Button = findViewById(R.id.submit)
        val questionField: EditText = findViewById(R.id.userQuestion)

        submitButton.setOnClickListener {
            val userQuestionInput = getUserInput(questionField)
            val response = Response().response(userQuestionInput, 6)
            Toast.makeText(applicationContext, "Your question: $userQuestionInput", Toast.LENGTH_SHORT).show()
            openAIResponse.text = response
        }
    }
}
