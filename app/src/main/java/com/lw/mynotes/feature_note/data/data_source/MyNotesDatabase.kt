package com.lw.mynotes.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lw.mynotes.feature_note.domain.model.Word


@Database(
    entities = [Word::class],
    version = 1
)
abstract class MyNotesDatabase: RoomDatabase() {

    abstract fun wordsDao(): WordsDao
}