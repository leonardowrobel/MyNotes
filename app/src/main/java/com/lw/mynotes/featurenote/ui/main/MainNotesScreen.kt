package com.lw.mynotes.featurenote.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lw.mynotes.featurenote.ui.components.Fab
import com.lw.mynotes.featurenote.ui.util.NavigationItem

@Composable
fun MainNotesScreen(
    viewModel: MainNotesViewModel = hiltViewModel(),
    navController: NavController
) {
    Surface(
        Modifier.fillMaxSize(), color = Color.LightGray
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(MainNotesViewModel.TAG)
                Button(onClick = {
                    navController.navigate(NavigationItem.AddEditNote.route)
                }) {
                    Text("AddEditNoteScreen")
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