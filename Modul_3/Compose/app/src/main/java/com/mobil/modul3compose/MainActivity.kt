package com.mobil.modul3compose

import android.graphics.Color
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            problemId = "2220A",
            title = "Blocked",
            description = "Determine if the array elements can be rearranged so that no identical subsets can be formed in the prefix.",
            tags = listOf("greedy", "constructive algorithms"),
            url = "https://codeforces.com/contest/2220/problem/A",
            solutionCode = "Belum ada",
            img = R.drawable.a_blocked
        ),
        CodeforcesProblem(
            problemId = "2209B",
            title = "Array Operations",
            description = "Process the sequence to find the optimal arrangement based on the given continuous interval conditions.",
            tags = listOf("implementation", "math"),
            url = "https://codeforces.com/contest/2209/problem/B",
            solutionCode = "Belum ada",
            img = R.drawable.b_array_operation
        ),
        CodeforcesProblem(
            problemId = "2209A",
            title = "Initial Configuration",
            description = "Find the minimum number of operations required to achieve the valid array state.",
            tags = listOf("greedy", "sortings"),
            url = "https://codeforces.com/contest/2209/problem/A",
            solutionCode = "Belum ada",
            img = R.drawable.a_initial_config
        ),
        CodeforcesProblem(
            problemId = "2125B",
            title = "Left and Down",
            description = "Navigate a 2D coordinate system using only 'Left' and 'Down' moves to calculate the required combinations.",
            tags = listOf("dp", "geometry"),
            url = "https://codeforces.com/contest/2125/problem/B",
            solutionCode = "Belum ada",
            img = R.drawable.b_left_and_down
        ),
        CodeforcesProblem(
            problemId = "2209C",
            title = "Find the Zero",
            description = "Interactive problem: You are given a hidden array of length 2n containing 1 to n and n zeros. Query pairs to find the positions of the zeroes.",
            tags = listOf("interactive", "constructive algorithms"),
            url = "https://codeforces.com/contest/2209/problem/C",
            solutionCode = "Belum ada",
            img = R.drawable.c_find_the_zero
        )
    )

    ProblemScreen(dummyProblemList)
}

@Composable
fun ProblemScreen(dummyProblemList: List<CodeforcesProblem>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item {
            ProblemCarousel(dummyProblemList)
        }
        items(dummyProblemList) { problem ->
            ProblemCard(problem)
        }
    }
}

@Composable
fun ProblemCard(problem: CodeforcesProblem) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = problem.img),
                contentDescription = "Problem ${problem.title} Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(128.dp)
                    .height(256.dp)
                    .clip(RoundedCornerShape(16.dp))
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
                .maskClip(MaterialTheme.shapes.extraLarge)
                .fillMaxSize(),
            painter = painterResource(item.img),
            contentDescription = item.title,
            contentScale = ContentScale.Crop
        )
    }
}