/*
    * Morgan's Reference: https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#

    * Copyright 2023 Ron Vincent V. Aspuria III
    * Copyright 2023 Taylor Asplund
*/
package com.comp350.die_cide

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.comp350.die_cide.QuestionInput.Companion.getUserInput
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var openAIResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openAIResponse = findViewById(R.id.textView6)

        // initialize variables for dice image, dice value, and user question
        val diceImage: ImageView = findViewById(R.id.diceBtn)
        var dieValue : Int
        val questionField: EditText = findViewById(R.id.userQuestion)
        var userQuestionInput: String
        var response: String?
        // button for image
        var micImage :ImageView

        // Actions to occur once dice is clicked on
        diceImage.setOnClickListener {
            dieValue = DiceLogic.roll()   // DICE LOGIC BLOCK
            DiceLogic.playDiceAnimation(diceImage)   // DICE ANIMATION BLOCK
            userQuestionInput = getUserInput(questionField)
            CoroutineScope(Dispatchers.Main).launch {
                response = withContext(Dispatchers.IO) {
                    Response().response(userQuestionInput, dieValue)
                }
                DiceLogic.displayDiceFace(diceImage, dieValue)
                openAIResponse.text = response
            }
        }
    }
}
