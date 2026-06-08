package com.mobil.modul5compose.ui.screens.home

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.mobil.modul5compose.R
import com.mobil.modul5compose.data.local.MovieEntity
import com.mobil.modul5compose.navigation.RoutingNames
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state.navigateToDetailEvent) {
        state.navigateToDetailEvent?.let { movieId ->
            Timber.d("Navigating to detail page for movieId: $movieId")
            navController.navigate(RoutingNames.DetailScreen(movieId))
            viewModel.onDetailNavigationHandled()
        }
    }

    LaunchedEffect(state.openExternalUrlEvent) {
        state.openExternalUrlEvent?.let { url ->
            Timber.d("Opening external URL: $url")
            context.startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
            viewModel.onExternalUrlHandled()
        }
    }

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
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (state.isLoading && state.movies.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                HomeContent(
                    movies = state.movies,
                    onExternalClick = viewModel::onExternalUrlClicked,
                    onDetailClick = viewModel::onMovieClicked
                )
            }

            state.errorMessage?.let { error ->
                Text(
                    text = error,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
private fun HomeContent(
    movies: List<MovieEntity>,
    onExternalClick: (String) -> Unit,
    onDetailClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        if (movies.isNotEmpty()) {
            item {
                MovieCarousel(
                    movies = movies.take(5),
                    onItemClick = onDetailClick
                )
            }

            items(
                items = movies,
                key = { it.id }
            ) { movie ->
                MovieCard(
                    movie = movie,
                    onExternalClick = { onExternalClick("https://www.themoviedb.org/movie/${movie.id}") },
                    onDetailClick = { onDetailClick(movie.id) }
                )
            }
        }
    }
}

@Composable
private fun MovieCard(
    movie: MovieEntity,
    onExternalClick: () -> Unit,
    onDetailClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable { onDetailClick() }
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
                contentDescription = "${movie.title} Poster",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = movie.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = onExternalClick,
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.OpenInBrowser,
                            contentDescription = "Open in TMDB",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Text(
                    text = "★ ${movie.voteAverage}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(vertical = 4.dp)
                )

                Text(
                    text = movie.overview,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieCarousel(
    movies: List<MovieEntity>,
    onItemClick: (Int) -> Unit
) {
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { movies.count() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 16.dp),
        preferredItemWidth = 372.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val movie = movies[i]

        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${movie.posterPath}",
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
                .maskClip(MaterialTheme.shapes.extraLarge)
                .clickable { onItemClick(movie.id) }
                .fillMaxSize()
        )
    }
}