package com.lw.mynotes.featurenote.ui.util

import javax.inject.Inject
import javax.inject.Singleton

enum class Screen {
    HOME,
    ADD_EDIT_NOTE,
    PROFILE
}

sealed class NavigationItem(val route: String) {
    object MainNotes: NavigationItem(Screen.HOME.name)
    object AddEditNote: NavigationItem(Screen.ADD_EDIT_NOTE.name)
    object Profile: NavigationItem(Screen.PROFILE.name)
}

// TODO: improve navigator
// https://stackoverflow.com/questions/75944895/navcontroller-in-viewmodel-androidx-navigation-navcontroller-cannot-be-provided
//@Singleton
//class AppNavigator @Inject constructor() {
//
//}