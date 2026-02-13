package com.lw.mynotes.featurenote.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lw.mynotes.featurenote.ui.components.Fab
import com.lw.mynotes.featurenote.ui.util.NavigationItem
import androidx.compose.runtime.collectAsState
import com.lw.mynotes.featurenote.ui.components.NoteCard

@Composable
fun MainNotesScreen (
    viewModel: MainNotesViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.getNotes()
        viewModel.navigationEvents.collect{ navEvent ->
            when(navEvent) {
                is NavigationEvent.NavigateToEdit -> {
                    navController.navigate(NavigationItem.AddEditNote.route + "?id=${navEvent.noteId}") // FIX-ME: goes to blank screen
                }
            }
        }
    }

    Surface(
        Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp), color = Color.White
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(horizontal = 14.dp, vertical = 18.dp),
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                if(viewModel.uiState.collectAsState().value.notes.isNotEmpty()){
                    for(note in viewModel.uiState.collectAsState().value.notes){
                        NoteCard(note = note, onClickEdit = { viewModel.editNote(note.id) })
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                } else {
                    Text("Lista vazia.")
                }
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(22.dp)
            ){
                Fab(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    onClick = { navController.navigate(NavigationItem.AddEditNote.route) })
            }

        }
    }
}