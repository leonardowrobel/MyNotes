package com.lw.mynotes.featurenote.data.data_source.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.lw.mynotes.featurenote.domain.model.Note

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