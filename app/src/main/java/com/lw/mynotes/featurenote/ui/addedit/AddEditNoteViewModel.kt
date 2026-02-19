package com.lw.mynotes.featurenote.ui.addedit

import android.util.Log
import android.widget.Toast
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

enum class ErrorType {
    NO_ERROR,
    MAX_TITLE_CHARACTER_REACHED,
    MAX_CONTENT_CHARACTER_REACHED,
}

data class AddEditNoteUiState(
    val note: Note? = null,
    val id: Long? = null,
    val title: String = "",
    val content: String = "",
    val errorType: ErrorType = ErrorType.NO_ERROR,
    val errorMessage: String = "",
    val message: String = ""
)

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    val notesService: NotesService,
): ViewModel() {

    private val _uiState = MutableStateFlow(AddEditNoteUiState())
    val uiState: StateFlow<AddEditNoteUiState> = _uiState.asStateFlow()

    fun loadNote(id: Long){
        viewModelScope.launch {
            val note = notesService.getById(id)
            _uiState.update { it.copy(note = note, title = note!!.title, content = note.content, id = note.id) }
        }
    }

    fun onTitleChanged(newTitle: String){
        _uiState.update { it.copy(title = newTitle) }
    }

    fun onContentChanged(newContent: String){
        _uiState.update { it.copy(content = newContent) }
    }

    fun clearData(){
        _uiState.update { AddEditNoteUiState() }
    }

    fun onError(type: ErrorType, msg: String){
        _uiState.update { it.copy(errorType = type, errorMessage = msg) }
    }

    fun clearError(){
        _uiState.update { it.copy(errorType = ErrorType.NO_ERROR, errorMessage = "") }
    }

    fun clearMessage(){
        _uiState.update { it.copy(message = "") }
    }

    fun create(){
        Log.d(TAG, "create()")
        viewModelScope.launch {
            notesService.createNote(_uiState.value.title, _uiState.value.content)
            _uiState.update { it.copy(message = "Criação de nota concluída.") }
            // TODO: go back to main page
        }
        clearData()
    }

    fun edit(){
        Log.d(TAG, "edit()")
    }

    companion object{
        const val TAG = "ADD_EDIT_NOTE_VM"
    }
}