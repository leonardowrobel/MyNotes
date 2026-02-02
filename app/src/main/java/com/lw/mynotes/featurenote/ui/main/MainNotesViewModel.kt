package com.lw.mynotes.featurenote.ui.main

import androidx.lifecycle.ViewModel
import com.lw.mynotes.featurenote.domain.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class MainNotesScreenState(
    val notes: List<Note> = listOf()
)

@HiltViewModel
class MainNotesViewModel @Inject constructor(): ViewModel() {



    companion object {
        const val TAG = "MAIN_NOTE_VM"
    }
}