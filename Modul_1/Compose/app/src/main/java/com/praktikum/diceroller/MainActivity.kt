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
        val result = remember { mutableStateListOf(1, 3) }

        val imageDice = result.map { res ->
            when (res) {
                0 -> R.drawable.dice_1
                1 -> R.drawable.dice_2
                2 -> R.drawable.dice_3
                3 -> R.drawable.dice_4
                4 -> R.drawable.dice_5
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
        Button(onClick = { result.replaceAll { (0..5).random() } }){
            Text(stringResource(R.string.roll))
        }
    }
}