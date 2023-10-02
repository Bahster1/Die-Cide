package com.comp350.die_cide

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Surface
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import com.comp350.die_cide.ui.theme.DieCideTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.isVisible
import com.comp350.die_cide.DiceLogic.*

//TODO: REMEMBER TO OPTIMIZE IMPORTS BEFORE FINAL COMMIT AND PUSH
class MainActivity : AppCompatActivity() {

    //private val useDice = DiceLogic()
    private var diceAnimation : AnimationDrawable? = null
    private var setTime : CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollDiceMA: ImageView = findViewById(R.id.diceBtn)
        rollDiceMA.setOnClickListener {
            onPlay()
            //rollDiceMA()
            //val toastMA = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_LONG)
            //toastMA.show()
        }

        /*setContent Start
        setContent {
            DieCideTheme {
                Surface(
                    modifier = Modifier
                ){
                   //useDice.clickDice()
                }
            }
        }
        //setContent End*/

    }

    private fun onPlay() {
        var randomTime = (1000..3000).random().toLong()
        setTime = object : CountDownTimer(randomTime,1000)
        {
            override fun onTick(p0: Long) {
                val diceImage : ImageView = findViewById(R.id.diceBtn)
                diceImage.setImageResource(androidx.appcompat.R.drawable.abc_control_background_material)
                diceImage.setBackgroundResource(R.drawable.animate_dice)
                diceAnimation = diceImage.background as AnimationDrawable
                diceAnimation?.start()
            }

            override fun onFinish() {
                diceAnimation?.stop()
                Toast.makeText(this@MainActivity,"DiceRolled!",Toast.LENGTH_SHORT).show()
            }

        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (setTime!=null)
        {
            setTime?.cancel()
        }
    }
/*
    private fun rollDiceMA(){
        val diceMA = DiceRollLogic(20)
        val diceResult = diceMA.rollDice()
        val diceImage : ImageButton = findViewById(R.id.diceBtn)
        //diceImage.setImageResource(R.drawable.dice_20)

        when(diceResult){
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

    class DiceRollLogic(private val diceMaxSides: Int){
        fun rollDice():Int{
            return(1..diceMaxSides).random()
        }
    }
*/
}
/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DieCideTheme {

    }
}
*/
