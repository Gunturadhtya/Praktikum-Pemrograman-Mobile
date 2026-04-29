package com.mobil.modul3compose

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(problemId: String, modifier: Modifier = Modifier) {
    val problem = ProblemRepository.getProblemById(problemId)

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Solution Code:", style = MaterialTheme.typography.labelLarge)

        Spacer(modifier = Modifier.height(8.dp))

        CodePreview(problem?.solutionCode ?: "No Solution Code")
    }
}

@Composable
fun CodePreview(code: String) {
    // Independent states for both directions
    val verticalScrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()

    SelectionContainer {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF1E1E1E))
                .verticalScroll(verticalScrollState)
                .padding(12.dp)
        ) {
            Text(
                text = code,
                color = Color(0xFFD4D4D4),
                fontFamily = FontFamily.Monospace,
                fontSize = 12.sp,
                softWrap = false,
                modifier = Modifier.horizontalScroll(horizontalScrollState)
            )
        }
    }
}