package com.comp350.die_cide

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

//TODO: REMEMBER TO OPTIMIZE IMPORTS BEFORE FINAL COMMIT AND PUSH
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollDiceMA: ImageView = findViewById(R.id.diceBtn)
        //rollDiceMA.setOnClickListener {
        //    DiceLogic.clickDice(rollDiceMA)
        //}

        rollDiceMA.setOnClickListener {
            DiceLogic.onPlay(rollDiceMA)   //CALL ANIMATION BLOCK

        }


    }

}

