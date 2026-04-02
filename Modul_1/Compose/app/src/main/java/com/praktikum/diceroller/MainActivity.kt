package com.praktikum.diceroller

import android.content.Context
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
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
        val result = remember { mutableStateListOf(0, 0) }

        val imageDice = result.map { res ->
            when (res) {
                0 -> R.drawable.dice_0
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
        }

        Row(){
           imageDice.forEachIndexed { index, img ->
               Image(
                   painter = painterResource(img),
                   contentDescription = result[index].toString(),
                   modifier = Modifier
                       .weight(1f)
               )
           }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            result.replaceAll { (1..6).random() }
            var prev = result.first()
            result.forEach { res ->
                if(prev != res) {
                    prev = -1
                }
            }

            if(prev != -1) Toast.makeText(context, context.getString(R.string.lucky), Toast.LENGTH_SHORT).show()
            else Toast.makeText(context, context.getString(R.string.badluck), Toast.LENGTH_SHORT).show()

        }){
            Text(stringResource(R.string.roll))
        }
    }
}