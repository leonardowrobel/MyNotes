package com.lw.mynotes.featurenote.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.data.model.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 4
)
abstract class MyNotesDatabase: RoomDatabase() {

    abstract val notesDao: NotesDao

    companion object {
        const val DATABASE_NAME = "my_notes_db"
    }
}