/*
    * Copyright 2023 Ron Vincent V. Aspuria III
*/

package com.comp350.die_cide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.comp350.die_cide.QuestionInput.Companion.getUserInput

class MainActivity : AppCompatActivity() {
    private lateinit var openAIResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openAIResponse = findViewById(R.id.textView6)

        // initialize variables for dice image and value
        val diceImage: ImageView = findViewById(R.id.diceBtn)
        var dieValue : Int
        val submitButton: Button = findViewById(R.id.submit)
        val questionField: EditText = findViewById(R.id.userQuestion)

        // Actions to occur once dice is clicked on
        diceImage.setOnClickListener {
            dieValue = DiceLogic.roll()             // DICE LOGIC BLOCK
            DiceLogic.onPlay(diceImage, dieValue)   // DICE ANIMATION BLOCK

            //testing to ensure "dieValue" is passed correctly - lives only in the Click event
            //val diceTest : TextView = findViewById(R.id.textView4)
            //diceTest.text = dieValue.toString()
        }

        submitButton.setOnClickListener {
            val userQuestionInput = getUserInput(questionField)
            val response = Response().response(userQuestionInput, 6)
            Toast.makeText(applicationContext, "Your question: $userQuestionInput", Toast.LENGTH_SHORT).show()
            openAIResponse.text = response
        }
    }
}
