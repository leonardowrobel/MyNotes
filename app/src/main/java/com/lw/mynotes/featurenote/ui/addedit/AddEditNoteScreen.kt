package com.lw.mynotes.featurenote.ui.addedit

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lw.mynotes.featurenote.ui.util.NavigationItem

@Composable
fun AddEditNoteScreen(
    viewModel: AddEditNoteViewModel = hiltViewModel(),
    noteId: Long? = null,
    navController: NavController
){
    val masTitleChar = 75
    val masContentChar = 500
    val mContext = LocalContext.current

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        if(noteId != null){
            viewModel.loadNote(noteId)
        }
        viewModel.navigationEvents.collect{ navEvent ->
            when(navEvent) {
                is NavigationEvent.NavigateToMain -> {
                    navController.navigate(NavigationItem.MainNotes.route)
                }
            }
        }
    }

    if(state.errorType != ErrorType.NO_ERROR){
        Toast.makeText(
            mContext,
            state.errorMessage,
            Toast.LENGTH_SHORT
        ).show()
        viewModel.clearError()
    }

    if(state.message.isNotEmpty()){
        Toast.makeText(
            mContext,
            state.message,
            Toast.LENGTH_SHORT
        ).show()
        viewModel.clearMessage()
    }

    Surface(
        Modifier.fillMaxSize(), color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 18.dp)
        ) {
            Spacer(modifier = Modifier.size(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = if(state.id == null) "Criar nota" else "Editar nota",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                if(state.id != null){
                    IconButton(
                        onClick = { viewModel.delete() }
                    ) {
                        Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete")
                    }
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.title,
                onValueChange = {
                    if(it.length <= masTitleChar){
                        viewModel.onTitleChanged(it)
                    } else {
                        viewModel.onError(
                            ErrorType.MAX_TITLE_CHARACTER_REACHED,
                            "Máximo de caracteres atingido!"
                        )
                    }
                },
                label = { Text("Título") },
                maxLines = 2
            )
            Spacer(modifier = Modifier.size(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().weight(1f),
                value = state.content,
                onValueChange = {
                    if(it.length <= masContentChar) {
                        viewModel.onContentChanged(it)
                    } else {
                        viewModel.onError(
                            ErrorType.MAX_CONTENT_CHARACTER_REACHED,
                            "Máximo de caracteres atingido!"
                        )
                    }
                },
                label = { Text("Conteúdo") }
            )
            Spacer(modifier = Modifier.size(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = state.status != AddEditNoteUiStatus.PRISTINE,
                onClick = { if(state.id == null) viewModel.create() else viewModel.edit()}) {
                    Text(if(state.id == null) "Criar" else "Editar")
                }
        }
    }
}