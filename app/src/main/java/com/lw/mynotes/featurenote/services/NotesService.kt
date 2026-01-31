package com.lw.mynotes.featurenote.services

import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.NoteRepository
import javax.inject.Inject

class NotesService @Inject constructor(
    val noteRepository: NoteRepository
) {
    suspend fun getAll(): List<Note> {
          return noteRepository.getAll().stream().map { it.toNote() }.toList()
    }

    suspend fun save(note: Note): Long {
        return noteRepository.insert(NoteEntity.from(note))
    }
}