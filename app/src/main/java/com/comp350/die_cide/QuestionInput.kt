/*
 * References:
 * https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#
 * https://stackoverflow.com/questions/55428670/where-to-write-functions-in-kotlin-that-will-be-used-from-other-activities-in-an
 *
 * Copyright 2023 Morgan McMurray and Bradley Walsh
 */

package com.comp350.die_cide

import android.widget.EditText
import androidx.compose.ui.text.input.TextFieldValue

class QuestionInput{

    companion object {

         private fun hasText(userQuestionTextBox: String): Boolean {
            return userQuestionTextBox.isNotEmpty()
         }


        // SPEECH TO TEXT BLOCK

        fun getUserQuestion(userQuestionTextBox: String): String {
            var question = ""

            if (hasText(userQuestionTextBox)) {
                question = userQuestionTextBox
            }

            return question
        }
    }
}