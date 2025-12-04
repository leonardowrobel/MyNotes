package com.lw.mynotes.feature_note.ui.notes

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NotesPage(
    viewModel: NotesViewModel = hiltViewModel()
) {
    Surface(
        Modifier.fillMaxSize()
            , color = Color.Red
    ) {
        Text(viewModel.name)
    }
}