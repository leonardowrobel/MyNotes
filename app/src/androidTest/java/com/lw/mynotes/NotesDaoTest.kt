package com.lw.mynotes

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.lw.mynotes.feature_note.data.data_source.MyNotesDatabase
import com.lw.mynotes.feature_note.data.data_source.NotesDao
import com.lw.mynotes.feature_note.domain.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
@SmallTest
class NotesDaoTest {
    private lateinit var database: MyNotesDatabase
    private lateinit var notesDao: NotesDao

    @Before
    fun setupDatabase(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MyNotesDatabase::class.java
        ).allowMainThreadQueries().build()

        notesDao = database.notesDao()
    }

    @After
    fun closeDatabase(){
        database.close()
    }

    @Test
    fun insertWord_returnsTrue() = runBlocking {
        val noteA = Note(title = "Note A", content = "This is the note's content")
        notesDao.insertNote(noteA)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            notesDao.getAllNotes().collect {
                assert(it.contains(noteA))
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }
}