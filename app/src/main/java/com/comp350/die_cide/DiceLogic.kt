/*
    * Copyright 2023 Ron Vincent V. Aspuria III
    * Copyright 2023 Taylor Asplund
*/
package com.comp350.die_cide

import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

// This is be the main view model file that will effect the view which is (activity_main.xml)

class DiceLogic {

    companion object : AppCompatActivity() {
        // LOGIC BLOCK
        private var dieValue = 0

        fun roll(): Int {
            dieValue = (1..20).random()
            return dieValue
        }

        // ANIMATION BLOCK
        private var diceAnimation: AnimationDrawable? = null
        // animate dice for indefinite amount of time
        fun onPlay(diceImage : ImageView) {
            diceImage.setImageResource(androidx.appcompat.R.drawable.abc_control_background_material)
            diceImage.setBackgroundResource(R.drawable.animate_dice)
            diceAnimation = diceImage.background as AnimationDrawable
            diceAnimation?.isOneShot = false
            diceAnimation?.start()
        }

        // call when we have a response from openai
        fun displayDiceFace(diceImage : ImageView , dieValue : Int) {
            diceAnimation?.stop()
            when (dieValue) {
                1 -> diceImage.setImageResource(R.drawable.dice_1)
                2 -> diceImage.setImageResource(R.drawable.dice_2)
                3 -> diceImage.setImageResource(R.drawable.dice_3)
                4 -> diceImage.setImageResource(R.drawable.dice_4)
                5 -> diceImage.setImageResource(R.drawable.dice_5)
                6 -> diceImage.setImageResource(R.drawable.dice_6)
                7 -> diceImage.setImageResource(R.drawable.dice_7)
                8 -> diceImage.setImageResource(R.drawable.dice_8)
                9 -> diceImage.setImageResource(R.drawable.dice_9)
                10 -> diceImage.setImageResource(R.drawable.dice_10)
                11 -> diceImage.setImageResource(R.drawable.dice_11)
                12 -> diceImage.setImageResource(R.drawable.dice_12)
                13 -> diceImage.setImageResource(R.drawable.dice_13)
                14 -> diceImage.setImageResource(R.drawable.dice_14)
                15 -> diceImage.setImageResource(R.drawable.dice_15)
                16 -> diceImage.setImageResource(R.drawable.dice_16)
                17 -> diceImage.setImageResource(R.drawable.dice_17)
                18 -> diceImage.setImageResource(R.drawable.dice_18)
                19 -> diceImage.setImageResource(R.drawable.dice_19)
                20 -> diceImage.setImageResource(R.drawable.dice_20)
            }
        }
    }
}


