package com.lw.mynotes.featurenote.ui.addedit

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(): ViewModel() {

    companion object{
        const val TAG = "ADD_EDIT_NOTE_VM"
    }
}