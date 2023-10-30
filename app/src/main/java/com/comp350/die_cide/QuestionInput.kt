/*
 * References:
 * https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#
 * https://stackoverflow.com/questions/55428670/where-to-write-functions-in-kotlin-that-will-be-used-from-other-activities-in-an
 *
 * Copyright 2023 Morgan McMurray and Bradley Walsh
 */

package com.comp350.die_cide

import android.app.Activity
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.EditText
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.comp350.die_cide.DiceLogic.Companion.registerForActivityResult
import java.util.*
/*import android.widget.Toast
import com.comp350.die_cide.DiceLogic.Companion.getApplicationContext*/

class QuestionInput (){

    companion object {
         private fun hasText(userQuestionTextBox: EditText): Boolean {
            return userQuestionTextBox.text.toString().isNotEmpty()
         }

        // SPEECH TO TEXT BLOCK
        /*
        fun setUpSpeechToText(): Intent {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            try {

                intent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
                )
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say your question...")
                //


            } catch (e:Exception){
                e.printStackTrace()
            }
            return intent

        }
         */

        fun getUserQuestion(userQuestionTextBox: EditText): String {
            var question = ""

            if (hasText(userQuestionTextBox)) {
                question = userQuestionTextBox.text.toString()
            }

            return question
        }
    }
}