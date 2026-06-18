package com.lw.mynotes.featurenote.services

import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.FirestoreNotesRepository
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SynchronizationService @Inject constructor(
    private val firestoreNotesRepository: FirestoreNotesRepository,
    private val notesRepository: NotesRepository,
    private val authenticationService: AuthenticationService,
    private val scope: CoroutineScope
) {
    private val isDataUnsynced = MutableStateFlow(false)

    private fun save(note: Note){
        scope.launch {
            firestoreNotesRepository.insert(note)
        }
    }

    private fun update(note: Note){
        scope.launch {
            firestoreNotesRepository.update(note)
        }
    }

    fun sync(note: Note){
        this.save(note)
    }

    // TODO: WIP
    // All logic here
    fun sync(localNotes: List<Note>){
        // First approach:
        val user = authenticationService.currentUser
        scope.launch {
            // 1. Get firestore notes
            val firestoreNotes = firestoreNotesRepository.getAllAsList(user.id).associateBy { it.id }
            // 2. Compare/Solve conflicts comparing lastUpdatedAt
            val updatedNotes = localNotes.map { note ->
                return@map if(firestoreNotes[note.id]!!.updatedAt > note.updatedAt) firestoreNotes[note.id] else note
            }

            // 3. Update local
            for (note in updatedNotes){
                notesRepository.update(NoteEntity.from(note!!))
            }
        }
        // 4. Update firestore
        for (note in localNotes){
            this.update(note)
        }
    }
}