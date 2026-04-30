package com.mobil.modul3compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DetailScreen(problemId: String, modifier: Modifier = Modifier) {
    val problem = ProblemRepository.getProblemById(problemId)

    if (problem == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Problem not found", style = MaterialTheme.typography.titleMedium)
        }
        return
    }

    val context = LocalContext.current
    val codeText = remember(problem.solutionCode) {
        context.resources.openRawResource(problem.solutionCode).bufferedReader().use { it.readText() }
    }

    Column(modifier = modifier.padding(16.dp)) {
        Image(
            painter = painterResource(problem.img),
            contentDescription = "Thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(128.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(problem.title), style = MaterialTheme.typography.labelLarge)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = stringResource(problem.description))

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Solution Code:", style = MaterialTheme.typography.labelLarge)

        Spacer(modifier = Modifier.height(8.dp))

        CodePreview(codeText)
    }
}

@Composable
fun CodePreview(code: String) {
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