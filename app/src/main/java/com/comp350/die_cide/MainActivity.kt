/*
    * Morgan's Reference: https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#

    * Copyright 2023 Ron Vincent V. Aspuria III
    * Copyright 2023 Taylor Asplund
*/
package com.comp350.die_cide

import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.comp350.die_cide.QuestionInput.Companion.getUserQuestion
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var openAIResponse: TextView

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openAIResponse = findViewById(R.id.textView6)


        val diceImage: ImageView = findViewById(R.id.diceBtn)
        var dieValue : Int
        var questionField : EditText = findViewById(R.id.userQuestion)
        var userQuestion: String
        var response: String?
        val micBtn: ImageView = findViewById(R.id.micImage)

        micBtn.setOnClickListener{

            /* VERSION 1
            questionField.text = null
            var speechToTextIntent = setUpSpeechToText()
            val result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    val results = result.data?.getStringArrayExtra(
                        RecognizerIntent.EXTRA_RESULTS
                    ) as ArrayList<String>

                    questionField.setText(results[0])
                }
            }
            result.launch(speechToTextIntent)
             */
        }

        // Actions to occur once dice is clicked on
        diceImage.setOnClickListener {
            userQuestion = getUserQuestion(questionField)

            // If the question field has no text or is entirely whitespace, a snackbar message appears at the bottom of the screen telling the user to enter a question
            // Otherwise, the dice rolls and a response from OpenAI is obtained
            if (userQuestion.isBlank()) {
                Snackbar.make(findViewById(R.id.MiddleConstraintLayout), "Please enter a question", Snackbar.LENGTH_SHORT).show()

            } else {
                dieValue = DiceLogic.roll()   // DICE LOGIC BLOCK
                DiceLogic.playDiceAnimation(diceImage)   // DICE ANIMATION BLOCK
                //TODO: diceImage.animate().rotation(1080f).setDuration(1000).start()

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
}
