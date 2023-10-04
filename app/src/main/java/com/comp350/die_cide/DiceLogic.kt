package com.comp350.die_cide

import android.graphics.drawable.AnimationDrawable
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

// this will be the main view model file that will effect the view which is (activity_main.xml)
//TODO: FILE IS WORK IN PROGRESS
//TODO: Return number correlating to dice face as a value

class DiceLogic {
    //fun clickDice(rollDiceMA : ImageView){}
    companion object : AppCompatActivity() {
        /*
        fun clickDice(rollDiceMA: ImageView) {

            val diceMA = DiceRollLogic(20)
            when (diceMA.rollDice()) {
                1 -> rollDiceMA.setImageResource(R.drawable.dice_1)
                2 -> rollDiceMA.setImageResource(R.drawable.dice_2)
                3 -> rollDiceMA.setImageResource(R.drawable.dice_3)
                4 -> rollDiceMA.setImageResource(R.drawable.dice_4)
                5 -> rollDiceMA.setImageResource(R.drawable.dice_5)
                6 -> rollDiceMA.setImageResource(R.drawable.dice_6)
                7 -> rollDiceMA.setImageResource(R.drawable.dice_7)
                8 -> rollDiceMA.setImageResource(R.drawable.dice_8)
                9 -> rollDiceMA.setImageResource(R.drawable.dice_9)
                10 -> rollDiceMA.setImageResource(R.drawable.dice_10)
                11 -> rollDiceMA.setImageResource(R.drawable.dice_11)
                12 -> rollDiceMA.setImageResource(R.drawable.dice_12)
                13 -> rollDiceMA.setImageResource(R.drawable.dice_13)
                14 -> rollDiceMA.setImageResource(R.drawable.dice_14)
                15 -> rollDiceMA.setImageResource(R.drawable.dice_15)
                16 -> rollDiceMA.setImageResource(R.drawable.dice_16)
                17 -> rollDiceMA.setImageResource(R.drawable.dice_17)
                18 -> rollDiceMA.setImageResource(R.drawable.dice_18)
                19 -> rollDiceMA.setImageResource(R.drawable.dice_19)
                20 -> rollDiceMA.setImageResource(R.drawable.dice_20)
            }


        }
        */

        private var diceAnimation: AnimationDrawable? = null
        private var setTime: CountDownTimer? = null
        fun onPlay(diceImage : ImageView) {
            val randomTime = (1000..3000).random().toLong()
            setTime = object : CountDownTimer(randomTime, 1000) {
                override fun onTick(p0: Long) {
                    diceImage.setImageResource(androidx.appcompat.R.drawable.abc_control_background_material)
                    diceImage.setBackgroundResource(R.drawable.animate_dice)
                    diceAnimation = diceImage.background as AnimationDrawable
                    diceAnimation?.start()
                }

                override fun onFinish() {
                    diceAnimation?.stop()
                    //Toast.makeText(this,"DiceRolled!",Toast.LENGTH_SHORT).show()
                }

            }.start()


        }

        override fun onDestroy() {
            super.onDestroy()
            if (setTime != null) {
                setTime?.cancel()
            }
        }


    }
}
/*
class DiceRollLogic(private val diceMaxSides: Int) {
    fun rollDice(): Int {
        return (1..diceMaxSides).random()
    }
}
*/

