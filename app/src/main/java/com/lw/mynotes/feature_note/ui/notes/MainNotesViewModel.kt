package com.lw.mynotes.feature_note.ui.notes

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainNotesViewModel @Inject constructor(): ViewModel() {

    companion object {
        const val TAG = "MAIN_NOTE_VM"
    }
}