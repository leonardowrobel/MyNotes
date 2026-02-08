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

data class AddEditNoteUiState(
    val note: Note? = null,
    val id: Long? = null,
    val title: String = "",
    val content: String = ""
)

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(
    val notesService: NotesService,
//    private val  navController: NavController
): ViewModel() {

    private val _uiState = MutableStateFlow(AddEditNoteUiState())
    val uiState: StateFlow<AddEditNoteUiState> = _uiState.asStateFlow()

    fun onTitleChanged(newTitle: String){
        _uiState.update { it.copy(title = newTitle) }
    }

    fun onContentChanged(newContent: String){
        _uiState.update { it.copy(content = newContent) }
    }

    fun clearData(){
        _uiState.update { AddEditNoteUiState() }
    }

    fun create(){
        Log.d(TAG, "create()")
        viewModelScope.launch {
            notesService.createNote(_uiState.value.title, _uiState.value.content)
            // TODO: toast presenting message for successfully note creation
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