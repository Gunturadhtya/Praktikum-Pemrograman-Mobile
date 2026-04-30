package com.mobil.modul3compose

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.core.net.toUri

@Composable
fun HomeScreen(navController: NavController, modifier: Modifier){
    ProblemScreen(ProblemRepository.getAllProblems(), navController, modifier)
}

@Composable
fun ProblemScreen(dummyProblemList: List<CodeforcesProblem>, navController: NavController ,modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize()
    ) {
        item{
            Button(
                onClick = {
                    navController.navigate(RoutingNames.LanguageScreen)
                }
            ){
                Text("*")
            }
        }
        item {
            ProblemCarousel(dummyProblemList)
        }
        items(dummyProblemList) { problem ->
            ProblemCard(problem, navController)
        }
    }
}

@Composable
fun ProblemCard(problem: CodeforcesProblem, navController: NavController) {

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
                    text = stringResource(problem.title),
                    modifier = Modifier
                        .padding(16.dp),
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(problem.description),
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
                        onClick = {
                            navController.context.startActivity(Intent(Intent.ACTION_VIEW,
                                problem.url.toUri()))
                        }
                    ) {
                        Text("Problem")
                    }
                    Button(
                        modifier = Modifier.padding(8.dp),
                        onClick = {
                            navController.navigate(RoutingNames.DetailScreen(problem.problemId))
                        }
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
            contentDescription = stringResource(item.title),
            contentScale = ContentScale.Crop
        )
    }
}