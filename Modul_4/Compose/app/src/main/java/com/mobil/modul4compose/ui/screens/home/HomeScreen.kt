package com.mobil.modul4compose.ui.screens.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mobil.modul4compose.R
import com.mobil.modul4compose.data.CodeforcesProblem
import com.mobil.modul4compose.navigation.RoutingNames

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(onClick = { navController.navigate(RoutingNames.LanguageScreen) }) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = "Change Language"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        HomeContent(
            problems = state.problems,
            onDetailClick = { id -> navController.navigate(RoutingNames.DetailScreen(id)) },
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun HomeContent(
    problems: List<CodeforcesProblem>,
    onDetailClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        if (problems.isNotEmpty()) {
            item {
                ProblemCarousel(
                    problems,
                    onDetailClick
                )
            }

            items(problems) { problem ->
                ProblemCard(
                    problem = problem,
                    onExternalClick = {
                        context.startActivity(Intent(Intent.ACTION_VIEW, problem.url.toUri()))
                    },
                    onDetailClick = { onDetailClick(problem.problemId) }
                )
            }
        }
    }
}

@Composable
fun ProblemCard(
    problem: CodeforcesProblem,
    onExternalClick: () -> Unit,
    onDetailClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = problem.img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.width(128.dp).height(256.dp).clip(RoundedCornerShape(16.dp))
            )
            Column {
                Text(
                    text = stringResource(problem.title),
                    modifier = Modifier.padding(16.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(problem.description),
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Justify,
                    fontSize = 16.sp
                )
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Button(onClick = onExternalClick, modifier = Modifier.padding(8.dp)) {
                        Text("Problem")
                    }
                    Button(onClick = onDetailClick, modifier = Modifier.padding(8.dp)) {
                        Text("Detail")
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProblemCarousel(
    problem: List<CodeforcesProblem>,
    onItemClick: (String) -> Unit
) {
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
                .clickable { onItemClick(item.problemId) }
                .maskClip(MaterialTheme.shapes.extraLarge)
                .fillMaxSize(),
            painter = painterResource(item.img),
            contentDescription = stringResource(item.title),
            contentScale = ContentScale.Crop
        )
    }
}