package com.lw.mynotes.featurenote.services

import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.FirestoreNoteRepository
import com.lw.mynotes.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesService @Inject constructor(
    val noteRepository: NoteRepository,
    val authenticationService: AuthenticationService,
    val firestoreNoteRepository: FirestoreNoteRepository
) {
    suspend fun getAll(): List<Note> {
          return noteRepository.getAll().stream().map { it.toNote() }.toList()
    }

    suspend fun getById(id: String): Note? {
        return noteRepository.get(id)?.toNote()
    }

    suspend fun saveLocal(note: Note) {
        noteRepository.insert(NoteEntity.from(note))
    }

    suspend fun save(note: Note) {
        firestoreNoteRepository.insert(note)
    }

    suspend fun update(note: Note) {
        return noteRepository.update(NoteEntity.from(note))
    }

    suspend fun createAndSave(title: String, content: String){
        withContext(Dispatchers.IO){
            val note = Note(title = title, content = content)
            if(authenticationService.currentUser.isAnonymous){
                saveLocal(note)
            } else {
              save(note)
            }
        }
    }

    suspend fun delete(note: Note){
        noteRepository.delete(NoteEntity.from(note))
    }

    companion object {
        const val TAG = "NOTES_SERV"
    }
}