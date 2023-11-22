/*
    * Copyright 2023 Ron Vincent V. Aspuria III
    * Copyright 2023 Taylor Asplund
*/
package com.comp350.die_cide

import android.animation.Animator
import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*


class DiceLogic {
    companion object : AppCompatActivity() {
        private var diceValue = 0

        fun roll(): Int {
            diceValue = (1..20).random()
            return diceValue
        }

        private var switchDiceFaceAnimation: AnimationDrawable? = null
        private var isDiceRotating = false
        private var shouldContinueRotation = true



        @Composable
        fun playDiceAnimation() {

        }


        // call when we have a response from openai
        @Composable
        fun displayDiceFace(diceValue : Int) : Int {
            return when (diceValue) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                6 -> R.drawable.dice_6
                7 -> R.drawable.dice_7
                8 -> R.drawable.dice_8
                9 -> R.drawable.dice_9
                10 -> R.drawable.dice_10
                11 -> R.drawable.dice_11
                12 -> R.drawable.dice_12
                13 -> R.drawable.dice_13
                14 -> R.drawable.dice_14
                15 -> R.drawable.dice_15
                16 -> R.drawable.dice_16
                17 -> R.drawable.dice_17
                18 -> R.drawable.dice_18
                19 -> R.drawable.dice_19
                else -> R.drawable.dice_20
            }
        }







    }
}


