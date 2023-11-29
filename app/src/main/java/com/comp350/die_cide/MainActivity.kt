/*
    * Morgan's Reference: https://www.geeksforgeeks.org/working-with-the-edittext-in-android/#
    * Bradley's Room DB Reference: https://developer.android.com/codelabs/android-room-with-a-view-kotlin#0
    *
    * Copyright 2023 Ron Vincent V. Aspuria III
    * Copyright 2023 Taylor Asplund
    * Copyright 2023 Bradley Walsh
*/
@file:OptIn(ExperimentalMaterial3Api::class)

package com.comp350.die_cide

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comp350.die_cide.data.Interaction
import com.comp350.die_cide.ui.theme.DieCideTheme
import com.comp350.die_cide.viewmodels.MainViewModel
import com.comp350.die_cide.viewmodels.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as DieCideApplication).repository)
    }

    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            val bitmap = getBitmapFromUri(uri)
            backgroundImage.setImageBitmap(bitmap)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    private lateinit var backgroundImage: ImageView

    private fun getBitmapFromUri(uri: Uri): Bitmap {
        val inputStream = contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        backgroundImage = findViewById(R.id.backgroundImage)

        findViewById<ComposeView>(R.id.TopComposeLayout).setContent {
            DieCideTheme {
                MainScreen()
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreenPreview() {
        MainScreen()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen() {
        var questionField by remember { mutableStateOf("") }
        var isKeyboardVisible by remember { mutableStateOf(true) }

        if (!isKeyboardVisible) {
            HideKeyboard()
        }

        Row {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Prompt field
                TextField(
                    value = questionField,
                    onValueChange = { questionField = it },
                    label = { Text("Type your question here:") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Button(
                    onClick = {
                        rollAction(questionField)
                        isKeyboardVisible = false
                    }
                ) {
                    Text(text = "ROLL!")
                }
            }
        }
    }

    private fun rollAction(questionField : String){
        val openAIResponseDisplay: TextView = findViewById(R.id.OpenAIResponse)
        val diceImage: ImageView = findViewById(R.id.diceBtn)
        val diceValue : Int
        var openAIResponse: String?
        var isDiceClickable = true

        isDiceClickable = DiceLogic.checkIfDiceIsClickable(isDiceClickable)

        if (questionField.isBlank()) {
            Snackbar.make(findViewById(R.id.MiddleConstraintLayout), "Please enter a question", Snackbar.LENGTH_SHORT).show()
        } else {
            while(!isDiceClickable){
                return
            }
            diceValue = DiceLogic.roll()   // DICE LOGIC BLOCK
            DiceLogic.playDiceAnimation(diceImage, 5000)   // DICE ANIMATION BLOCK

            // Enables dice animation to run throughout the duration of obtaining an OpenAI response
            CoroutineScope(Dispatchers.Main).launch {
                openAIResponse = withContext(Dispatchers.IO) {
                    Response().getResponse(questionField, diceValue)
                }

                DiceLogic.displayDiceFace(diceImage, diceValue)
                openAIResponseDisplay.text = openAIResponse
                mainViewModel.insert(Interaction(question = questionField, number = diceValue, answer = openAIResponse!!))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.history -> startActivity(Intent(this, HistoryActivity::class.java))
            R.id.background -> pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        return super.onOptionsItemSelected(item)
    }

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun HideKeyboard(){
        val keyboardController = LocalSoftwareKeyboardController.current
        keyboardController?.hide()
    }
}
