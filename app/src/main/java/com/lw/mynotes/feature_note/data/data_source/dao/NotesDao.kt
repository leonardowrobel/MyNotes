package com.lw.mynotes.feature_note.data.data_source.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.lw.mynotes.feature_note.domain.model.Note

@Dao
interface NotesDao: BaseDAO<Note> {
    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<Note>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun get(id: Long): Note

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(vararg notes: Note)
}