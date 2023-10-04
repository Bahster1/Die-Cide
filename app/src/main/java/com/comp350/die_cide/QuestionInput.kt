/*
 * References:
 * https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#
 * https://stackoverflow.com/questions/55428670/where-to-write-functions-in-kotlin-that-will-be-used-from-other-activities-in-an
 *
 * Code written by Morgan McMurray with some assistance from Bradley Walsh
 */

package com.comp350.die_cide

import android.widget.EditText

 class QuestionInput (){

    companion object {
         fun hasText(userQuestionTextBox: EditText): Boolean {
            return !userQuestionTextBox.text.toString().isEmpty()
         }

        fun getUserInput(userQuestionTextBox: EditText): String {
            var retVal = ""

            if (Companion.hasText(userQuestionTextBox)) {
                retVal = userQuestionTextBox.text.toString()
            }

            return retVal
        }
    }
}