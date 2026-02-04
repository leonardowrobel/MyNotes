package com.lw.mynotes.featurenote.data.data_source.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.lw.mynotes.featurenote.data.model.NoteEntity

@Dao
interface NotesDao: BaseDAO<NoteEntity> {
    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun get(id: Long): NoteEntity

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(vararg notes: NoteEntity)
}