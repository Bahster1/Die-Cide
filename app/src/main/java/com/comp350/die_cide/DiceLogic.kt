/*
    * Copyright 2023 Ron Vincent V. Aspuria III
    * Copyright 2023 Taylor Asplund
*/
package com.comp350.die_cide

import android.animation.Animator
import android.graphics.drawable.AnimationDrawable
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


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

//        private fun createDiceFaceAnimator(diceImage: ImageView, duration: Long): ValueAnimator {
//            val frameArray = intArrayOf(
//                R.drawable.dice_1,
//                R.drawable.dice_2,
//                R.drawable.dice_3,
//                R.drawable.dice_4,
//                R.drawable.dice_5,
//                R.drawable.dice_6,
//                R.drawable.dice_7,
//                R.drawable.dice_8,
//                R.drawable.dice_9,
//                R.drawable.dice_10,
//                R.drawable.dice_11,
//                R.drawable.dice_12,
//                R.drawable.dice_13,
//                R.drawable.dice_14,
//                R.drawable.dice_15,
//                R.drawable.dice_16,
//                R.drawable.dice_17,
//                R.drawable.dice_18,
//                R.drawable.dice_19,
//                R.drawable.dice_20
//            )
//
//            val faceAnimator = ValueAnimator()
//            faceAnimator.setIntValues(*frameArray)
//            faceAnimator.duration = duration
//            faceAnimator.addUpdateListener { animation ->
//                val drawableResId = animation.animatedValue as Int
//                val drawable = diceImage.context.getDrawable(drawableResId)
//                diceImage.setImageDrawable(drawable)
//            }
//
//            return faceAnimator
//        }

        fun playDiceAnimation(diceImage : ImageView, duration: Long) {
            // TODO: TASK 4b
            setUpDiceEnvironment(diceImage)
            switchDiceFaceAnimation?.start()

//            val faceAnimator = createDiceFaceAnimator(diceImage, duration)
//            faceAnimator.start()
            shouldContinueRotation = true
            rotateDice(diceImage)

        }

        private fun setUpDiceEnvironment(diceImage: ImageView){
            // Dice image is replaced with temporary transparent drawable for background animation
            diceImage.setImageResource(androidx.appcompat.R.drawable.abc_control_background_material)
            diceImage.setBackgroundResource(R.drawable.animate_dice)
            switchDiceFaceAnimation = diceImage.background as AnimationDrawable
            switchDiceFaceAnimation?.isOneShot = false

        }

        // call when we have a response from openai
        fun displayDiceFace(diceImage : ImageView , diceValue : Int) {
            switchDiceFaceAnimation?.stop()
            shouldContinueRotation = false
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

        private fun rotateDice (diceImage: ImageView){
            if (!shouldContinueRotation) {
                return // Stop the animation
            }

            diceImage
                .animate()
                .rotation(720f)
                .setDuration(500)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(animation: Animator) {
                        isDiceRotating = true
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        // Animation ends, restart it
                        diceImage.rotation = 0f // Reset the rotation
                        isDiceRotating = false
                        rotateDice(diceImage)
                    }

                    override fun onAnimationCancel(animation: Animator) {}

                    override fun onAnimationRepeat(animation: Animator) {}
                })
                .start()
        }






    }
}


