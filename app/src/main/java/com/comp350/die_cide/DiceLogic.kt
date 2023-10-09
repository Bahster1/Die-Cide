/*
    * Copyright 2023 Ron Vincent V. Aspuria III
*/
package com.comp350.die_cide

import android.graphics.drawable.AnimationDrawable
import android.os.CountDownTimer
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
        var diceAnimation: AnimationDrawable? = null
        private var setTime: CountDownTimer? = null
        fun onPlay(diceImage : ImageView , dieValue : Int){
            val randomTime = (1000..3000).random().toLong()
            setTime = object : CountDownTimer(randomTime, 1000) {
                // For every Tick, a random dice image will be selected and replace the dice image
                override fun onTick(p0: Long) {
                    diceImage.setImageResource(androidx.appcompat.R.drawable.abc_control_background_material)
                    diceImage.setBackgroundResource(R.drawable.animate_dice)
                    diceAnimation = diceImage.background as AnimationDrawable
                    diceAnimation?.start()
                }
                // Once finished, the animation will end on a dice face based on 'dieValue'
                override fun onFinish() {
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

            }.start()

        }
        // Function to stop 'setTime' Timer to prevent infinite counter
        override fun onDestroy() {
            super.onDestroy()
            if (setTime != null) {
                setTime?.cancel()
            }
        }


    }
}


