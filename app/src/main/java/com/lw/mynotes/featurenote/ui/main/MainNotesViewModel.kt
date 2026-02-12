package com.lw.mynotes.featurenote.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.services.NotesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MainNotesUiState(
    val notes: List<Note> = listOf()
)

@HiltViewModel
class MainNotesViewModel @Inject constructor(
    private val notesService: NotesService
): ViewModel() {

    private val _uiState = MutableStateFlow(MainNotesUiState())
    val uiState: StateFlow<MainNotesUiState> = _uiState.asStateFlow()

    init {
        getNotes()
    }

    fun getNotes() {
        viewModelScope.launch {
            _uiState.update { it.copy(notes = notesService.getAll()) }
        }
    }

    fun editNote(id: Long){

    }

    companion object {
        const val TAG = "MAIN_NOTE_VM"
    }
}