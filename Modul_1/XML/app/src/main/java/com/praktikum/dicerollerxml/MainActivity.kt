package com.praktikum.dicerollerxml

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.praktikum.dicerollerxml.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var diceResults = listOf(0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateUi(diceResults)
        binding.rollButton.setOnClickListener {
            diceResults = rollDice(diceResults.size)
            updateUi(diceResults)
            Toast.makeText(this, getString(checkLuck(diceResults)), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUi(results : List<Int>) {
        binding.diceContainer.removeAllViews()
        results.forEach { result ->
            val diceView = ImageView(this).apply {
                layoutParams = LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1.0f
                ).apply {
                    setMargins(8,8,8,8)
                }
                setImageResource(getDrawableDice(result))
                contentDescription = result.toString()
            }
            binding.diceContainer.addView(diceView)
        }
    }

    fun getDrawableDice(value: Int): Int = when (value) {
        0 -> R.drawable.dice_0
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    fun rollDice(count: Int = 2): List<Int> = List(count) {(1..6).random()}

    fun checkLuck(results: List<Int>): Int =
        if (results.all { it == results.first() }) R.string.lucky
        else R.string.badluck


}