package com.lw.mynotes.featurenote.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.data.data_source.dao.WordsDao
import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.data.model.Word


@Database(
    entities = [Word::class, NoteEntity::class],
    version = 2
)
abstract class MyNotesDatabase: RoomDatabase() {

    abstract fun wordsDao(): WordsDao
    abstract val notesDao: NotesDao

    companion object {
        const val DATABASE_NAME = "my_notes_db"
    }
}