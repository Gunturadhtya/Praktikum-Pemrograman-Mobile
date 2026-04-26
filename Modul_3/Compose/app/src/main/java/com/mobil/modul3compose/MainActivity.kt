package com.mobil.modul3compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobil.modul3compose.ui.theme.Modul3ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul3ComposeTheme {
                Scaffold() { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding))
                }

            }
        }
    }
}
@Composable
fun HomeScreen(modifier: Modifier){
    val dummyProblemList = listOf(
        CodeforcesProblem(
            problemId = "4A",
            title = "Watermelon",
            description = "Determine if a watermelon of weight w can be divided into two parts, each weighing an even number of kilos.",
            tags = listOf("brute force", "math"),
            url = "https://codeforces.com/problemset/problem/4/A",
            solutionCode = "Belum ada"
        ),
        CodeforcesProblem(
            problemId = "71A",
            title = "Way Too Long Words",
            description = "Abbreviate words longer than 10 characters by replacing the middle with the count of omitted letters.",
            tags = listOf("strings"),
            url = "https://codeforces.com/problemset/problem/71/A",
            solutionCode = "Belum ada"
        ),
        CodeforcesProblem(
            problemId = "1A",
            title = "Theatre Square",
            description = "Find the minimum number of a x a flagstones needed to cover an n x m rectangular square.",
            tags = listOf("math"),
            url = "https://codeforces.com/problemset/problem/1/A",
            solutionCode = "Belum ada"
        ),
        CodeforcesProblem(
            problemId = "158A",
            title = "Next Round",
            description = "Calculate how many contestants will advance to the next round based on a k-th place finisher's score.",
            tags = listOf("implementation"),
            url = "https://codeforces.com/problemset/problem/158/A",
            solutionCode = "Belum ada"
        ),
        CodeforcesProblem(
            problemId = "50A",
            title = "Domino piling",
            description = "Find the maximum number of 2x1 dominoes that can be placed on an M x N board.",
            tags = listOf("greedy", "math"),
            url = "https://codeforces.com/problemset/problem/50/A",
            solutionCode = "Belum ada"
        )
    )

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        ProblemCarousel(dummyProblemList)
        LazyColumn() {
            items(dummyProblemList) {problem ->
                ProblemCard(problem)
            }
        }
    }
}

@Composable
fun ProblemCard(problem: CodeforcesProblem) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Row() {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "Problem ${problem.title} Image"
            )
            Column() {
                Text(
                    text = problem.title,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = problem.description,
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp
                )
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {}
                    ) {
                        Text("Problem")
                    }
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {}
                    ) {
                        Text("Detail")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProblemCarousel(problem: List<CodeforcesProblem>) {
    data class CarouselItem(
        val id: Int,
        @DrawableRes val imageResId: Int,
        val contentDescription: String
    )

    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { problem.count() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 16.dp),
        preferredItemWidth = 372.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = problem[i]
        Image(
            modifier = Modifier
                .height(205.dp)
                .maskClip(MaterialTheme.shapes.extraLarge),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = item.title,
            contentScale = ContentScale.Crop
        )
    }
}