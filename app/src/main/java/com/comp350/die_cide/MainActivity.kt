/*
    * Morgan's EditTest Reference: https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#
    * Morgan's Composable EditText Reference: https://www.geeksforgeeks.org/edittext-in-android-using-jetpack-compose/#
    * Bradley's Room DB Reference: https://developer.android.com/codelabs/android-room-with-a-view-kotlin#0
    *
    * Copyright 2023 Ron Vincent V. Aspuria III
    * Copyright 2023 Taylor Asplund
    * Copyright 2023 Bradley Walsh
*/
package com.comp350.die_cide

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.comp350.die_cide.QuestionInput.Companion.getUserQuestion
import com.comp350.die_cide.data.Interaction
import com.comp350.die_cide.data.InteractionDao
import com.comp350.die_cide.data.InteractionRoomDatabase
import com.comp350.die_cide.ui.theme.DieCideTheme
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// TODO LIST:
// [DONE]  Make sure all necessary compose libraries are in the gradle files
// [DONE] Add new directories in "res" and "java/com/comp350/die_cide" to populate with new UI/UX data, in accordance with standard Jetpack Compose development
// [DONE - Github takes care of this] Make copies of existing Activities, which will house the converted code
// [IN PROGRESS] Within the copies, convert existing code using XML to code using Jetpack Compose. This will also require making those new UI/UX data files/kotlin classes/etc. in the new directories
// Rigorously test the converted code
// Delete the old XML-based code once it is clear that the Jetpack Compose code works

class MainActivity : AppCompatActivity() {

    private lateinit var openAIResponseDisplay: TextView
    private lateinit var db: InteractionRoomDatabase
    private lateinit var interactionDao: InteractionDao
    private lateinit var interaction: Interaction
    private lateinit var questionField: EditText
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setContent {
            DieCideTheme {
                // A surface container using the 'background' color from the theme
                MainScreenPreview()
            }
        }
        openAIResponseDisplay = findViewById(R.id.OpenAIResponse)
        db = InteractionRoomDatabase.getDatabase(this)
        interactionDao = db.interactionDao()


        val diceImage: ImageView = findViewById(R.id.diceBtn)
        var diceValue: Int
        questionField = findViewById(R.id.userQuestion)
        var userQuestion: String
        var openAIResponse: String?
        val micBtn: ImageView = findViewById(R.id.micImage)


        micBtn.setOnClickListener {
            questionField.text = null
            startSpeechToText()
        }


        diceImage.setOnClickListener {
            userQuestion = getUserQuestion(questionField)


            if (userQuestion.isBlank()) {
                Snackbar.make(
                    findViewById(R.id.MiddleConstraintLayout),
                    "Please enter a question",
                    Snackbar.LENGTH_SHORT
                ).show()

            } else {
                hideKeyboard()
//                val mediaPlayer = MediaPlayer.create(this,R.raw.rolling_dice_sfx)
//                mediaPlayer.start()
                diceValue = DiceLogic.roll()   // DICE LOGIC BLOCK
                DiceLogic.playDiceAnimation(diceImage, 5000)   // DICE ANIMATION BLOCK


                // Enables dice animation to run throughout the duration of obtaining an OpenAI response
                CoroutineScope(Dispatchers.Main).launch {
                    openAIResponse = withContext(Dispatchers.IO) {
                        Response().getResponse(userQuestion, diceValue)
                    }

                    DiceLogic.displayDiceFace(diceImage, diceValue)
                    openAIResponseDisplay.text = openAIResponse


                    interaction = Interaction(
                        question = userQuestion,
                        number = diceValue,
                        answer = openAIResponse
                    )
                    interactionDao.insert((interaction))
                }

//                mediaPlayer.release()
            }
        }
    }

    // SPEECH TO TEXT BLOCK
    // 10/30/23 - This will be implemented in QuestionInput.kt later and cleaned up. For the
    // sprint demo, I assumed working code is better than clean code FOR NOW.
    // THIS WAS CHOSEN FOR CLASS COHESION - PUT THIS IN QuestionInput.kt or in a new class
    private fun startSpeechToText() {
        val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        speechIntent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")

        try {
            startActivityForResult(speechIntent, 1)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "Speech recognition not available on this device",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    //Confirms use of startSpeechToText function
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (!result.isNullOrEmpty()) {
                val spokenText = result[0]
                questionField.setText(spokenText)

            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history -> startActivity(Intent(this, HistoryActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }

    private fun hideKeyboard() {
        val keyboardHider = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboardHider.hideSoftInputFromWindow(questionField.windowToken, 0)
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreenPreview() {
        MainScreen()
    }

    @Composable
    fun MainScreen() {
        // TODO: THIS WILL BE WHERE ALL UI ELEMENTS WILL GO
        Column {
            Text("Die-Cide")
        }
    }

}