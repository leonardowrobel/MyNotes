package com.lw.mynotes.featurenote.services

import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.FirestoreNoteRepository
import com.lw.mynotes.featurenote.services.network.NetworkConnectivityService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SynchronizationService @Inject constructor(
    private val firestoreNoteRepository: FirestoreNoteRepository,
    private val authenticationService: AuthenticationService,
    private val scope: CoroutineScope
) {
    private val isDataUnsynced = MutableStateFlow(false)
//    private val isDeviceConnected = MutableStateFlow(false)

    private fun save(note: Note){
        scope.launch {
            firestoreNoteRepository.insert(note)
        }
    }

    fun sync(note: Note){
        this.save(note)
    }

    // All logic here
    fun sync(notes: List<Note>){
        // First approach:
        // 1. Get firestore notes
        // 2. Compare/Solve conflicts comparing lastUpdatedAt
        // 3. Update local
        // 4. Update firestore
        val user = authenticationService.currentUser
        scope.launch {
            val firestoreNotes = firestoreNoteRepository.getAll(user.id)

        }
        for (note in notes){
            this.save(note)
        }
    }
}