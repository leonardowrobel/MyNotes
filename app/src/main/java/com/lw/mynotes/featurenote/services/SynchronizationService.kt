package com.lw.mynotes.featurenote.services

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestoreException
import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.FirestoreNotesRepository
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.map

class SynchronizationService @Inject constructor(
    private val firestoreNotesRepository: FirestoreNotesRepository,
    private val notesRepository: NotesRepository,
    private val authenticationService: AuthenticationService,
    private val scope: CoroutineScope
) {
    private val isDataUnsynced = MutableStateFlow(false)

    private suspend fun save(note: Note){
//        scope.launch {
            firestoreNotesRepository.insert(note)
//        }
    }

    private fun update(note: Note){
        scope.launch {
            firestoreNotesRepository.update(note)
        }
    }

    // TODO:
//    fun sync(note: Note){
//        this.save(note)
//    }

    // TODO: WIP
    // All logic here
    fun sync(localNotes: List<Note>){
        Log.d(TAG, "sync(List<Note>))")
        // First approach:
        val user = authenticationService.currentUser
        scope.launch {
            // 1. Get firestore notes
            lateinit var firestoreNotes: List<Note>
            lateinit var firestoreNotesMap: Map<Long, Note>
            lateinit var syncedNotes: List<Note>
            try {
                firestoreNotes = firestoreNotesRepository.getAllAsList(user.id)
            } catch (e: FirebaseFirestoreException){
                Log.e(TAG, "Error trying to get all as list:")
                e.message
            }
            // 1.1 Check if there's no notes on cloud
            if(firestoreNotes.isEmpty()){ // TODO: Check this
                firestoreNotesMap = firestoreNotes.associateBy { it.id }
                // 2. Compare/Solve conflicts comparing lastUpdatedAt
                syncedNotes = localNotes.map { note ->
                    return@map (if(firestoreNotesMap[note.id]!!.updatedAt > note.updatedAt) firestoreNotesMap[note.id] else note)!!
                }
                // 3. Update local
    //            for (note in updatedNotes){
    //                try {
    //                    notesRepository.update(NoteEntity.from(note!!))
    //                } catch (e: FirebaseFirestoreException){
    //                    Log.e(TAG, "Error trying to updating")
    //                    e.message
    //                }
    //            }
                // 4. Update firestore
//                for (note in localNotes){
//                    update(note)
//                }
            } else {
                // 4. Update firestore
                for (note in localNotes){
                    save(note)
                }
            }

        }
    }

    companion object {
        const val TAG = "SYNC_SER"
    }
}