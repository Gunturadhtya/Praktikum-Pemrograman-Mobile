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
    private var diceResults = listOf(1, 3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateUi(diceResults)
        binding.rollButton.setOnClickListener {
            diceResults = rollDice(2)
            updateUi(diceResults)
            Toast.makeText(this, getString(checkLuck(diceResults)), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUi(results : List<Int>) {
        val views = listOf(binding.diceImage1, binding.diceImage2)

        views.zip(results).forEach { (view, result) ->
            view.setImageResource(getDrawableDice(result))
            view.contentDescription = result.toString()
        }
    }

    fun getDrawableDice(value: Int): Int = when (value) {
        0 -> R.drawable.dice_1
        1 -> R.drawable.dice_2
        2 -> R.drawable.dice_3
        3 -> R.drawable.dice_4
        4 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    fun rollDice(count: Int = 2): List<Int> = List(count) {(1..6).random()}

    fun checkLuck(results: List<Int>): Int =
        if (results.all { it == results.first() }) R.string.lucky
        else R.string.badluck


}