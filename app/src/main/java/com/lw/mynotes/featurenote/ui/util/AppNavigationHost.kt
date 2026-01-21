package com.lw.mynotes.featurenote.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lw.mynotes.featurenote.ui.addedit.AddEditNoteScreen
import com.lw.mynotes.featurenote.ui.main.MainNotesScreen

@Composable
fun AppNavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.MainNotes.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(NavigationItem.MainNotes.route){
            MainNotesScreen(navController = navController)
        }
        composable(NavigationItem.AddEditNote.route){
            AddEditNoteScreen(navController = navController)
        }
    }
}