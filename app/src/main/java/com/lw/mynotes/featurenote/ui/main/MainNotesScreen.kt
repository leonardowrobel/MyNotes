package com.lw.mynotes.featurenote.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lw.mynotes.featurenote.ui.components.Fab
import com.lw.mynotes.featurenote.ui.util.NavigationItem
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import com.lw.mynotes.featurenote.ui.components.NoteCard

@OptIn(ExperimentalMaterial3Api::class)
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
                    navController.navigate(NavigationItem.AddEditNote.route + "?id=${navEvent.noteId}")
                }
                is NavigationEvent.NavigateToProfile -> {
                    navController.navigate(NavigationItem.Profile.route)
                }
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Notes",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary)
                }, actions = {
                    IconButton(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 18.dp, vertical = 10.dp),
                        onClick =  { viewModel.goToProfile() }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Profile",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            )
        },
        floatingActionButton = {
            Fab(onClick = { navController.navigate(NavigationItem.AddEditNote.route) })
        }
    ) { innerPadding ->
        Surface(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
            }
        }
    }
}