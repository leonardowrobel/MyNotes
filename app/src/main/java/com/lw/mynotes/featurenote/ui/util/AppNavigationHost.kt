package com.lw.mynotes.featurenote.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
        composable(NavigationItem.AddEditNote.route + "?id={id}", arguments = listOf(
            navArgument("id"){
                type = NavType.StringType
                nullable = true
            }
        )){ backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            AddEditNoteScreen(navController = navController, noteId = id?.toLongOrNull())
        }
    }
}