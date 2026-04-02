package com.praktikum.dicerollerxml

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.praktikum.dicerollerxml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val diceResults = mutableListOf(1, 3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateUi()

        binding.rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {

        diceResults[0] = (0..5).random()
        diceResults[1] = (0..5).random()
        updateUi()

        if(diceResults[0] == diceResults[1]) Toast.makeText(this, getString(R.string.lucky), Toast.LENGTH_SHORT).show()
        else Toast.makeText(this, getString(R.string.badluck), Toast.LENGTH_SHORT).show()
    }

    private fun updateUi() {
        val views = listOf(binding.diceImage1, binding.diceImage2)

        diceResults.forEachIndexed { index, result ->
            val drawableRes = when (result) {
                0 -> R.drawable.dice_1
                1 -> R.drawable.dice_2
                2 -> R.drawable.dice_3
                3 -> R.drawable.dice_4
                4 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            views[index].setImageResource(drawableRes)
            views[index].contentDescription = result.toString()
        }
    }
}