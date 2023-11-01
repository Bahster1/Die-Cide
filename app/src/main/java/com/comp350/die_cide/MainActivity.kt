/*
    * Morgan's Reference: https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#
    * Bradley's Room DB Reference: https://developer.android.com/codelabs/android-room-with-a-view-kotlin#0
    *
    * Copyright 2023 Ron Vincent V. Aspuria III
    * Copyright 2023 Taylor Asplund
    * Copyright 2023 Bradley Walsh
*/
package com.comp350.die_cide

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.comp350.die_cide.QuestionInput.Companion.getUserInput
import com.comp350.die_cide.data.Interaction
import com.comp350.die_cide.data.InteractionDao
import com.comp350.die_cide.data.InteractionRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var openAIResponse: TextView
    private lateinit var db: InteractionRoomDatabase
    private lateinit var interactionDao: InteractionDao
    private lateinit var interaction: Interaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openAIResponse = findViewById(R.id.textView6)
        db = InteractionRoomDatabase.getDatabase(this)
        interactionDao = db.interactionDao()

        // initialize variables for dice image and value
        val diceImage: ImageView = findViewById(R.id.diceBtn)
        var dieValue : Int
        val questionField: EditText = findViewById(R.id.userQuestion)
        var userQuestionInput: String
        var response: String?

        // Actions to occur once dice is clicked on
        diceImage.setOnClickListener {
            dieValue = DiceLogic.roll()   // DICE LOGIC BLOCK
            DiceLogic.onPlay(diceImage)   // DICE ANIMATION BLOCK
            userQuestionInput = getUserInput(questionField)
            CoroutineScope(Dispatchers.Main).launch {
                response = withContext(Dispatchers.IO) {
                    Response().response(userQuestionInput, dieValue)
                }
                DiceLogic.displayDiceFace(diceImage, dieValue)
                openAIResponse.text = response

                interaction = Interaction(question = userQuestionInput, number = dieValue, answer = response)
                interactionDao.insert((interaction))
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
}
