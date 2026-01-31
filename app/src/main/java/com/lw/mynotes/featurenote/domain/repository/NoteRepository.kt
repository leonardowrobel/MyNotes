package com.lw.mynotes.featurenote.domain.repository

import com.lw.mynotes.featurenote.data.model.NoteEntity

interface NoteRepository {

    // TODO: implement this
//    fun getAll(): Flow<List<Note>>
    suspend fun getAll(): List<NoteEntity>

    suspend fun get(id: Long): NoteEntity?

    suspend fun insert(note: NoteEntity): Long

    suspend fun update(note: NoteEntity)

    suspend fun delete(note: NoteEntity)
}