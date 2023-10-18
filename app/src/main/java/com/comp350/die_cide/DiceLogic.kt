/*
    * Copyright 2023 Ron Vincent V. Aspuria III
    * Copyright 2023 Taylor Asplund
*/
package com.comp350.die_cide

import android.graphics.drawable.AnimationDrawable
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

// This is be the main view model file that will effect the view which is (activity_main.xml)

class DiceLogic {

    companion object : AppCompatActivity() {
        // LOGIC BLOCK
        private var diceValue = 0

        fun roll(): Int {
            diceValue = (1..20).random()
            return diceValue
        }

        // ANIMATION BLOCK
        private var diceAnimation: AnimationDrawable? = null

        // animate dice for indefinite amount of time
        fun playDiceAnimation(diceImage : ImageView) {
            setUpDiceEnvironment(diceImage)
            diceAnimation?.start()
        }

        private fun setUpDiceEnvironment(diceImage: ImageView){
            // Dice image is replaced with transparent Android Studio drawable...
            diceImage.setImageResource(androidx.appcompat.R.drawable.abc_control_background_material)
            // ...then the background is set to a drawable that goes through the dice faces.
            diceImage.setBackgroundResource(R.drawable.animate_dice)
            diceAnimation = diceImage.background as AnimationDrawable
            diceAnimation?.isOneShot = false
        }

        // call when we have a response from openai
        fun displayDiceFace(diceImage : ImageView , diceValue : Int) {
            diceAnimation?.stop()
            when (diceValue) {
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


