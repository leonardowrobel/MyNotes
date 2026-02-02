package com.lw.mynotes.featurenote.ui.addedit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun AddEditNoteScreen(
    viewModel: AddEditNoteViewModel = hiltViewModel(),
    navController: NavController
){
    val state by viewModel.uiState.collectAsState()

    Surface(
        Modifier.fillMaxSize(), color = Color.Cyan
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(12.dp)
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            Text(if(state.id == null) "Criar nota" else "Editar nota")
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = state.title,
                onValueChange = { viewModel.onTitleChanged(it) },
                label = { Text("Título") }
            )
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                value = state.content,
                onValueChange = { viewModel.onContentChanged(it) },
                label = { Text("Conteúdo") }
            )
            Spacer(modifier = Modifier.size(8.dp))
            Button(onClick = {
                if(state.id == null) viewModel.create() else viewModel.edit()
            }) {
                Text(if(state.id == null) "Criar" else "Editar")
            }
        }
    }
}