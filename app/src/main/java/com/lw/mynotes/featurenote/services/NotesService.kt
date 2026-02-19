package com.lw.mynotes.featurenote.services

import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesService @Inject constructor(
    val noteRepository: NoteRepository
) {
    suspend fun getAll(): List<Note> {
          return noteRepository.getAll().stream().map { it.toNote() }.toList()
    }

    suspend fun getById(id: Long): Note? {
        return noteRepository.get(id)?.toNote()
    }

    suspend fun save(note: Note): Long {
        return noteRepository.insert(NoteEntity.from(note))
    }

    suspend fun update(note: Note) {
        return noteRepository.update(NoteEntity.from(note))
    }

    suspend fun create(title: String, content: String){
        withContext(Dispatchers.IO){
            val note = Note(title = title, content = content)
            save(note)
        }
    }

    suspend fun delete(note: Note){
        noteRepository.delete(NoteEntity.from(note))
    }
}