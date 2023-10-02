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
        rollDiceMA.setOnClickListener {
            DiceLogic.clickDice(rollDiceMA)
        }

       // rollDiceMA.setOnClickListener {
            //onPlay()   CALL ANIMATION BLOCK

       // }


    }
    // VARIABLES FOR ANIMATION
    //private var diceAnimation : AnimationDrawable? = null
    //private var setTime : CountDownTimer? = null

    // START ANIMATION BLOCK
    /*
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

    */

}

