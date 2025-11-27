package com.lw.mynotes

import android.util.Log
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
import java.text.SimpleDateFormat
import java.util.Date

@RunWith(AndroidJUnit4::class)
@SmallTest
class NotesDaoTest {

    companion object {
        const val TAG = "NOTES_DAO_TEST"
    }
    private lateinit var database: MyNotesDatabase
    private lateinit var notesDao: NotesDao
    private lateinit var dateFormat: SimpleDateFormat

    @Before
    fun setupDatabase(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MyNotesDatabase::class.java
        ).allowMainThreadQueries().build()

        notesDao = database.notesDao()
    }

    @Before
    fun setup(){
        dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:SSS")
    }

    @After
    fun closeDatabase(){
        database.close()
    }

    @Test
    fun insertNote_returnsTrue() = runBlocking {
        val noteA = Note(title = "Note A", content = "This is the note's content")
        notesDao.insert(noteA)

        notesDao.getAllNotes().let {
            val note = it[0]
            Log.d(TAG, note.toString())
            Log.d(TAG, "Note created at: " + dateFormat.format(Date(note.createdAt)))
            assert(it.contains(noteA))
        }
    }

    @Test
    fun updateNote_returnsTrue() = runBlocking {
        val noteB = Note(title = "Note B", content = "This is an unchanged content.")
        var updatedNote = Note(title = "", content = "")
        notesDao.insert(noteB)
        notesDao.getAllNotes().let {
            updatedNote = it[0].copy(title = "Updated note B", content = "This is the new changed content.")
        }
        notesDao.updateNote(updatedNote)
        notesDao.getAllNotes().let {
            Log.d(TAG, it[0].toString())
            assert(it[0].content == updatedNote.content)
        }
    }

    @Test
    fun updateNoteUsingId_returnsTrue() = runBlocking {
        val noteC = Note(title = "Note C", content = "This is an unchanged content.")
        val noteCId = notesDao.insert(noteC)
        val updatedNote = Note(id = noteCId, title = "Updated note C", content = "This is the new changed content.")

        notesDao.updateNote(updatedNote)
        notesDao.getAllNotes().let {
            Log.d(TAG, it[0].toString())
            assert(it[0].content == updatedNote.content)
        }
    }
}