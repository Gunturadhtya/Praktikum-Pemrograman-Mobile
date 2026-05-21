package com.mobil.modul5compose.ui.screens.language

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mobil.modul5compose.R

@Composable
fun LanguageScreen(
    navController: NavController,
    viewModel: LanguageViewModel
) {
    val state by viewModel.uiState.collectAsState()

    LanguageContent(
        state = state,
        onBackClick = { navController.popBackStack() },
        onLanguageClick = { viewModel.onLanguageSelected(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LanguageContent(
    state: LanguageState,
    onBackClick: () -> Unit,
    onLanguageClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.language_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            state.languages.forEach { language ->
                LanguageItem(
                    title = language.name,
                    tag = language.tag,
                    currentTag = state.selectedTag,
                    onClick = { onLanguageClick(language.tag) }
                )
            }
        }
    }
}

@Composable
private fun LanguageItem(
    title: String,
    tag: String,
    currentTag: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)
        if (currentTag == tag) {
            Icon(Icons.Default.Check, contentDescription = "Selected")
        }
    }
}