package com.comp350.die_cide

import android.widget.ImageView

// this will be the main view model file that will effect the view which is (activity_main.xml)
//TODO: FILE IS WORK IN PROGRESS

class DiceLogic {
   //fun clickDice(rollDiceMA : ImageView){}
    companion object  {
       fun clickDice(rollDiceMA: ImageView) {

           val diceMA = DiceRollLogic(20)
           when (diceMA.rollDice()){
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
   }

}

class DiceRollLogic(private val diceMaxSides: Int){
    fun rollDice():Int{
        return(1..diceMaxSides).random()
    }
}

