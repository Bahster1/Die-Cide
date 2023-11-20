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


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.compose.setContent
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.comp350.die_cide.data.Interaction
import com.comp350.die_cide.data.InteractionDao
import com.comp350.die_cide.data.InteractionRoomDatabase
import com.comp350.die_cide.ui.theme.DieCideTheme
import kotlinx.coroutines.*


// TODO LIST:
// [DONE]  Make sure all necessary compose libraries are in the gradle files
// [DONE] Add new directories in "res" and "java/com/comp350/die_cide" to populate with new UI/UX data, in accordance with standard Jetpack Compose development
// [DONE - Github takes care of this] Make copies of existing Activities, which will house the converted code
// [IN PROGRESS] Within the copies, convert existing code using XML to code using Jetpack Compose. This will also require making those new UI/UX data files/kotlin classes/etc. in the new directories
// Rigorously test the converted code
// Delete the old XML-based code once it is clear that the Jetpack Compose code works
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : AppCompatActivity() {
    // TODO: Adapt these left over variables (if applicable)
//    private lateinit var openAIResponseDisplay: TextView
    private lateinit var db: InteractionRoomDatabase
    private lateinit var interactionDao: InteractionDao
    private lateinit var interaction: Interaction
//    private lateinit var questionField: EditText

    //Background variables
    // Define a state variable to hold the URI of the selected image
    private var backgroundImageUri by mutableStateOf<Uri?>(null)
    // Registers a photo picker activity launcher in single-select mode.
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
            backgroundImageUri = uri // Update the state variable
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = InteractionRoomDatabase.getDatabase(this)
        interactionDao = db.interactionDao()
        setContent {
            DieCideTheme {
                // A surface container using the 'background' color from the theme
                MainScreenPreview()
            }
        }
    }




    @Preview(showBackground = true)
    @Composable
    fun MainScreenPreview() {
        MainScreen()
    }

    @Composable
    fun MainScreen() {
        // TODO: BRING BACK ALL NECESSARY VARIABLES
        //Question prompt variables
        var questionField by remember { mutableStateOf("") }
        var userQuestion by remember { mutableStateOf("") }
        //Open AI variables
        var openAIResponse by remember { mutableStateOf("") }
        var openAIResponseDisplay by remember { mutableStateOf("") }
        //Dice logic variables
        var diceValue : Int
        //Speech-to-text variables
        //Rooms Database variables

        Box(modifier = Modifier.fillMaxSize()) {
            // Display the selected image as a background if it's not null
            backgroundImageUri?.let { uri ->
                AsyncImage(
                    model = uri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Row {
                // Title and Menu button

            }
            Row {
                Column (modifier = Modifier
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
                    // Mic Button for Speech to text
                    Image(
                        painter = painterResource(id = R.drawable.mic_image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(16.dp)
                            .clickable {
                                // Speech-to-text logic
                                questionField = ""
                                // startSpeechToText()
                            }
                    )

                    // Dice image
                    Image(
                        painter = painterResource(id = R.drawable.dice_20),
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .padding(16.dp)
                            .clickable {
                                userQuestion = QuestionInput.getUserQuestion(questionField)


                                diceValue = DiceLogic.roll()
                                //TODO Dice Animation here

                                // Enables dice animation to run throughout the duration of obtaining an OpenAI response
                                CoroutineScope(Dispatchers.Main).launch {
                                    openAIResponse = withContext(Dispatchers.IO) {
                                        Response().getResponse(userQuestion, diceValue).toString()
                                    }
                                    // TODO Display dice face here

                                    openAIResponseDisplay = "$openAIResponse AND Dice Value $diceValue" //Dice value added to output for testing

                                    interaction = Interaction(question = userQuestion, number = diceValue, answer = openAIResponse)
                                    interactionDao.insert((interaction))

                                }

                            }
                    )

                    // Response field
                    Text(
                        text = openAIResponseDisplay,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }


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
            R.id.background -> pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        return super.onOptionsItemSelected(item)
    }

}