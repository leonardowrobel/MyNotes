package com.lw.mynotes.featurenote.domain.repository

import com.lw.mynotes.featurenote.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface FirestoreNoteRepository {

    // TODO: implement this
    suspend fun getAll(userId: String): Flow<List<Note>>
//    suspend fun getAll(): List<Note>

    suspend fun get(id: String): Note?

    suspend fun insert(note: Note)
//    suspend fun insert(note: Note): Long

    suspend fun update(note: Note)

//    suspend fun delete(note: Note)
}