package com.comp350.die_cide

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// this will be the main view model file that will effect the view which is (activity_main.xml)
//TODO: FILE IS WORK IN PROGRESS

class DiceLogic : AppCompatActivity() {
    fun clickDice() {
        val rollDiceMA: ImageView = findViewById(R.id.diceBtn)
        rollDiceMA.setOnClickListener{
            val toastMA = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_LONG)
            toastMA.show()
        }

    }
}

