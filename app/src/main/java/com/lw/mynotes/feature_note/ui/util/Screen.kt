package com.lw.mynotes.feature_note.ui.util

sealed class Screen(val route: String) {
    object MainNotes: Screen("main_notes_screen")
    object AddEditNote: Screen("add_edit_note_screen")
}