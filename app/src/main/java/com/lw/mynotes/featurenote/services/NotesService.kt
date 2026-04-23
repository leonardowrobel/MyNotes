package com.lw.mynotes.featurenote.services

import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.FirestoreNoteRepository
import com.lw.mynotes.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NotesService @Inject constructor(
    val noteRepository: NoteRepository,
    val firestoreNoteRepository: FirestoreNoteRepository,
    val authenticationService: AuthenticationService
) {
    suspend fun getAll(): List<Note> {
        return if(authenticationService.currentUser.isAnonymous){
            noteRepository.getAll().stream().map { it.toNote() }.toList()
        } else {
            firestoreNoteRepository.getAll(authenticationService.currentUser.id).first()
        }
    }

    private suspend fun getAllLocal(): List<Note> {
        return noteRepository.getAll().stream().map { it.toNote() }.toList()
    }

    suspend fun getById(id: Long): Note? {
//        return if(authenticationService.currentUser.isAnonymous) {
            return noteRepository.get(id)?.toNote()
//        } else {
//            firestoreNoteRepository.get(id)
//        }
    }

    suspend fun createAndSave(title: String, content: String){
        withContext(Dispatchers.IO){
            if(authenticationService.currentUser.isAnonymous){
                val note = Note(title = title, content = content)
                saveLocal(note)
            } else {
                val note = Note(title = title, content = content, userId = authenticationService.currentUser.id)
                saveRemote(note)
            }
        }
    }

    private suspend fun saveLocal(note: Note) {
        noteRepository.insert(NoteEntity.from(note))
    }

    private suspend fun saveRemote(note: Note) {
        firestoreNoteRepository.insert(note)
    }

    suspend fun update(note: Note) {
        if(authenticationService.currentUser.isAnonymous) {
            noteRepository.update(NoteEntity.from(note))
        } else {
            firestoreNoteRepository.update(note)
        }
    }

    suspend fun delete(note: Note){
        if(authenticationService.currentUser.isAnonymous) {
            noteRepository.delete(NoteEntity.from(note))
        } else {
            firestoreNoteRepository.delete(note)
        }
    }

    private suspend fun deleteLocal(note: Note){
        noteRepository.delete(NoteEntity.from(note))
    }

    suspend fun sync(cleanLocal: Boolean = false){
        val notesLocal = getAllLocal()
        if(notesLocal.isNotEmpty()){
            for (note in notesLocal){
                saveRemote(note.copy(userId = authenticationService.currentUser.id))
                if(cleanLocal){
                    deleteLocal(note)
                }
            }
        }
    }

    companion object {
        const val TAG = "NOTES_SERV"
    }
}