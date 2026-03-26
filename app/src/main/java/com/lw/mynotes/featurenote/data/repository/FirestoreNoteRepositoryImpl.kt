package com.lw.mynotes.featurenote.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.FirestoreNoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirestoreNoteRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): FirestoreNoteRepository {

    override suspend fun getAll(userId: String): Flow<List<Note>> {
        return firestore.collection(NOTES_COLLECTION)
            .whereEqualTo(USER_ID_FIELD, userId)
            .dataObjects<Note>()
    }

//    override suspend fun get(id: Long): NoteEntity? {
//        return dao.get(id)
//    }

    override suspend fun insert(note: Note){
           Log.d(TAG, "insert()")
           Log.d(TAG, "title: ${note.title}")
       firestore.collection("notes").add(note)
           .addOnSuccessListener { documentReference ->
               Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//               return@addOnSuccessListener documentReference.id.toLong()
           }
           .addOnFailureListener { e ->
               Log.w(TAG, "Error adding document", e)
           }
    }

//    override suspend fun update(note: NoteEntity) {
//        dao.update(note)
//    }

//    override suspend fun delete(note: NoteEntity) {
//        dao.delete(note)
//    }

    companion object {
        const val TAG = "FIRESTORE_REPO"
        private const val USER_ID_FIELD = "userId"
        private const val NOTES_COLLECTION = "notes"
    }
}