package com.lw.mynotes.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lw.mynotes.feature_note.domain.model.Note
import com.lw.mynotes.feature_note.domain.model.Word


@Database(
    entities = [Word::class, Note::class],
    version = 2
)
abstract class MyNotesDatabase: RoomDatabase() {

    abstract fun wordsDao(): WordsDao
    abstract fun notesDao(): NotesDao
}