package com.comp350.die_cide

import android.widget.EditText

// Is there a way I can link this class to the MainActivity?
// Is there a way I can initialize this class with the appview or something?
// I don't think I should have this as a subclass of MainActivity
    // Especially if this class needs to be used in the MainActivity class

// Methods I should put:
    // hasText(): is there text in the text field?
    // getUserInput(): get the user input from the text field
    // showToastFeedback(): show toasts regarding whether or not the user entered a question

 class QuestionInput (){
    //val userQuestionTextBox: EditText = findViewById(R.id.editText)

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