package com.lw.mynotes.featurenote.services

import android.os.Build
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.NoteRepository
import javax.inject.Inject

class NoteService @Inject constructor(
    val noteRepository: NoteRepository
) {
    suspend fun getAll(): List<Note> {
          return noteRepository.getAll().stream().map { it.toNote() }.toList()
    }
}