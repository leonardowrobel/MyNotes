package com.lw.mynotes.featurenote.domain.repository

import com.lw.mynotes.featurenote.domain.model.Note

interface NoteRepository {

    // TODO: implement this
//    fun getAll(): Flow<List<Note>>
    suspend fun getAll(): List<Note>

    suspend fun get(id: Long): Note?

    suspend fun insert(note: Note)

    suspend fun update(note: Note)

    suspend fun delete(note: Note)
}