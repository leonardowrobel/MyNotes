package com.lw.mynotes.feature_note.ui.add_edit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(): ViewModel() {

    companion object{
        const val TAG = "ADD_EDIT_NOTE_VM"
    }
}