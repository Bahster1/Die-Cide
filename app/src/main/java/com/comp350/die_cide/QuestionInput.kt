/*
 * References:
 * https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#
 * https://stackoverflow.com/questions/55428670/where-to-write-functions-in-kotlin-that-will-be-used-from-other-activities-in-an
 *
 * Copyright 2023 Morgan McMurray and Bradley Walsh
 */

package com.comp350.die_cide

import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class QuestionInput (){
     // lateinit var micImage: ImageView

    companion object {
         private fun hasText(userQuestionTextBox: EditText): Boolean {
            return userQuestionTextBox.text.toString().isNotEmpty()
         }

        fun getUserInput(userQuestionTextBox: EditText): String {
            var retVal = ""

            if (hasText(userQuestionTextBox)) {
                retVal = userQuestionTextBox.text.toString()
            }

            return retVal
        }
    }
}