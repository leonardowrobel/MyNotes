package com.lw.mynotes.featurenote.services

import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import javax.inject.Inject

class NotesService @Inject constructor(
    val notesRepository: NotesRepository,
//    val firestoreNoteRepository: FirestoreNoteRepository,
//    val authenticationService: AuthenticationService
) {
    suspend fun getAll(): List<Note> {
        return notesRepository.getAll().stream().map { it.toNote() }.toList()
    }

    suspend fun getById(id: Long): Note? {
        return notesRepository.get(id)?.toNote()
    }

    private suspend fun save(note: Note) {
        notesRepository.insert(NoteEntity.from(note))
    }

    fun create(title: String, content: String): Note {
        return Note(title = title, content = content)
    }

    suspend fun createAndSave(title: String, content: String){
        this.save(this.create(title, content))
    }

    suspend fun update(note: Note) {
        notesRepository.update(NoteEntity.from(note))
    }

    suspend fun delete(note: Note){
        notesRepository.delete(NoteEntity.from(note))
    }

    companion object {
        const val TAG = "NOTES_SERV"
    }
}