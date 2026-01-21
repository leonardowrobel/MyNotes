package com.lw.mynotes.featurenote.ui.util

enum class Screen {
    HOME,
    ADD_EDIT_NOTE
}

sealed class NavigationItem(val route: String) {
    object MainNotes: NavigationItem(Screen.HOME.name)
    object AddEditNote: NavigationItem(Screen.ADD_EDIT_NOTE.name)
}