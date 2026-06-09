package com.lw.mynotes.featurenote.services

import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import javax.inject.Inject

class NotesService @Inject constructor(
    private val notesRepository: NotesRepository,
//    val firestoreNoteRepository: FirestoreNoteRepository,
    val authenticationService: AuthenticationService
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

    suspend fun associateCurrentUser(note: Note){
        if(note.userId.isNotEmpty())
            return
        val noteToUpdate = note.copy(userId = authenticationService.currentUserId)
        this.update(noteToUpdate)
    }

    suspend fun associateCurrentUserToLocalNotes(){
        if(authenticationService.currentUser.isAnonymous)
            return
        val notes = this.getAll()
        for(note in notes){
            associateCurrentUser(note)
        }
    }

    companion object {
        const val TAG = "NOTES_SERV"
    }
}