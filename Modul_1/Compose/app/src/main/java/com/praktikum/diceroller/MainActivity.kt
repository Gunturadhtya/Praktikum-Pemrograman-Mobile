package com.praktikum.diceroller

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.praktikum.diceroller.ui.theme.DiceRollerTheme
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerTheme {
                DiceRollerApp()
            }
        }
    }
}

@Preview
@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        var diceResult by remember { mutableStateOf(listOf(0, 0))}

        Row{
           diceResult.forEach { value ->
               Log.i("Dice Render", "This dice is render with value $value")
               Image(
                   painter = painterResource(getDrawableDice(value)),
                   contentDescription = value.toString(),
                   modifier = Modifier
                       .weight(1f)
               )
           }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            diceResult = rollDice(diceResult.size)
            Toast.makeText(context, context.getString(checkLuck(diceResult)), Toast.LENGTH_SHORT).show()
        }){
            Text(stringResource(R.string.roll))
        }
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

fun rollDice(count: Int = 2): List<Int> = List(count) {(1 .. 6).random()}

fun checkLuck(result: List<Int>): Int =
    if(result.all {it == result.first()}) R.string.lucky
    else R.string.badluck