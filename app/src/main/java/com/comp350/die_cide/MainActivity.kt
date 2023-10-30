/*
    * Morgan's Reference: https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#

    * Copyright 2023 Ron Vincent V. Aspuria III
    * Copyright 2023 Taylor Asplund
*/
package com.comp350.die_cide

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.comp350.die_cide.QuestionInput.Companion.getUserQuestion
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : AppCompatActivity() {
    private lateinit var openAIResponse: TextView
    private lateinit var questionField : EditText

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openAIResponse = findViewById(R.id.textView6)


        val diceImage: ImageView = findViewById(R.id.diceBtn)
        var dieValue : Int
        questionField = findViewById(R.id.userQuestion)
        var userQuestion: String
        var response: String?
        val micBtn: ImageView = findViewById(R.id.micImage)

        micBtn.setOnClickListener{
            questionField.text = null
            startSpeechToText()
        }


        diceImage.setOnClickListener {
            userQuestion = getUserQuestion(questionField)

            if (userQuestion.isBlank()) {
                Snackbar.make(findViewById(R.id.MiddleConstraintLayout), "Please enter a question", Snackbar.LENGTH_SHORT).show()

            } else {
                dieValue = DiceLogic.roll()   // DICE LOGIC BLOCK
                DiceLogic.playDiceAnimation(diceImage, 5000)   // DICE ANIMATION BLOCK

                // Enables dice animation to run throughout the duration of obtaining an OpenAI response
                CoroutineScope(Dispatchers.Main).launch {
                    response = withContext(Dispatchers.IO) {
                        Response().response(userQuestion, dieValue)
                    }
                    DiceLogic.displayDiceFace(diceImage, dieValue)
                    openAIResponse.text = response
                }
            }

        }
    }

    // SPEECH TO TEXT BLOCK
    // 10/30/23 - This will be implemented in QuestionInput.kt later and cleaned up. For the
    // sprint demo, I assumed working code is better than clean code FOR NOW.
    private fun startSpeechToText() {
        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")

        try {
            startActivityForResult(speechIntent, 1)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Speech recognition not available on this device", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (result != null && result.isNotEmpty()) {
                val spokenText = result[0]
                questionField.setText(spokenText)
            }
        }
    }
}
