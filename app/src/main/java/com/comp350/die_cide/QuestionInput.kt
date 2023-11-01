/*
 * References:
 * https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#
 * https://stackoverflow.com/questions/55428670/where-to-write-functions-in-kotlin-that-will-be-used-from-other-activities-in-an
 *
 * Copyright 2023 Morgan McMurray and Bradley Walsh
 */

package com.comp350.die_cide


import android.widget.EditText
/*import android.widget.Toast
import com.comp350.die_cide.DiceLogic.Companion.getApplicationContext*/

class QuestionInput (){

    companion object {

        // Returns True if the passed EditText element has text in it
        // Returns False if the passed EditText element does not have text in it
         private fun hasText(userQuestionTextBox: EditText): Boolean {
            return userQuestionTextBox.text.toString().isNotEmpty()
         }


        // SPEECH TO TEXT BLOCK

        fun getUserQuestion(userQuestionTextBox: EditText): String {
            var question = ""

            if (hasText(userQuestionTextBox)) {
                question = userQuestionTextBox.text.toString()
            }

            return question
        }
    }
}