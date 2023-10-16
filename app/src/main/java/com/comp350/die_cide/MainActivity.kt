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
    private lateinit var openAIResponseDisplay: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openAIResponseDisplay = findViewById(R.id.OpenAIResponse)

        // initialize variables for dice image and value
        val diceImage: ImageView = findViewById(R.id.diceBtn)
        var diceValue : Int
        val questionField: EditText = findViewById(R.id.userQuestion)
        var userQuestionInput: String
        var openAIResponse: String?

        // Actions to occur once dice is clicked on
        diceImage.setOnClickListener {
            diceValue = DiceLogic.roll()   // DICE LOGIC BLOCK
            DiceLogic.onPlay(diceImage)   // DICE ANIMATION BLOCK
            userQuestionInput = getUserInput(questionField)
            CoroutineScope(Dispatchers.Main).launch {
                openAIResponse = withContext(Dispatchers.IO) {
                    Response().getResponse(userQuestionInput, diceValue)
                }
                DiceLogic.displayDiceFace(diceImage, diceValue)
                openAIResponseDisplay.text = openAIResponse
            }
        }
    }
}
