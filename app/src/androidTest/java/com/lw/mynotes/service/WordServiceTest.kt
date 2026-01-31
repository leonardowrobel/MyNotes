package com.lw.mynotes.service

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.lw.mynotes.featurenote.data.data_source.MyNotesDatabase
import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.data.repository.NoteRepositoryImpl
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class WordServiceTest {

    private lateinit var database: MyNotesDatabase
    private lateinit var notesDao: NotesDao
    private lateinit var notesRepository: NoteRepositoryImpl

    @Before
    fun setupDatabase(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MyNotesDatabase::class.java
        ).allowMainThreadQueries().build()

        notesDao = database.notesDao
        notesRepository = NoteRepositoryImpl(notesDao)
    }

    @After
    fun closeDatabase(){
        database.close()
    }

    companion object {
        const val TAG = "WORD_SERV_TEST"
    }
}