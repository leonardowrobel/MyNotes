package com.lw.mynotes

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.lw.mynotes.featurenote.data.data_source.MyNotesDatabase
import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.data.model.NoteEntity
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

        notesDao = database.notesDao
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
        val firstNoteToInset = NoteEntity(title = "Note A", content = "This is the note's content")
        val idFromDB = notesDao.insert(firstNoteToInset)
        val firstNoteToInsetWithId = firstNoteToInset.copy(id = idFromDB)

        notesDao.getAll().let {
            val note = it[0]
            Log.d(TAG, note.toString())
            Log.d(TAG, "Note created at: " + dateFormat.format(Date(note.createdAt)))
            assert(it.contains(firstNoteToInsetWithId))
        }
    }

    @Test
    fun insertNotes_returnsTrue() = runBlocking {
        val noteA = NoteEntity(title = "Note A", content = "This is the note's content")
        val noteB = NoteEntity(title = "Note B", content = "This is another note's content")
        val noteC = NoteEntity(title = "Note C", content = "Yet another note's content")

        val listOfIds = notesDao.insert(listOf(noteA, noteB, noteC))
        val noteBWithId = noteB.copy(id = listOfIds[1])

        notesDao.getAll().let {
            val note = it[0]
            Log.d(TAG, note.toString())
            Log.d(TAG, "Note created at: " + dateFormat.format(Date(note.createdAt)))
            assert(it.contains(noteBWithId))
        }
    }

    @Test
    fun updateNote_returnsTrue() = runBlocking {
        val noteB = NoteEntity(title = "Note B", content = "This is an unchanged content.")
        var updatedNote = NoteEntity(title = "", content = "")
        notesDao.insert(noteB)
        notesDao.getAll().let {
            updatedNote = it[0].copy(title = "Updated note B", content = "This is the new changed content.")
        }
        notesDao.update(updatedNote)
        notesDao.getAll().let {
            Log.d(TAG, it[0].toString())
            assert(it[0].content == updatedNote.content)
        }
    }

    @Test
    fun updateNoteUsingId_returnsTrue() = runBlocking {
        val noteC = NoteEntity(title = "Note C", content = "This is an unchanged content.")
        val noteCId = notesDao.insert(noteC)
        val updatedNote = NoteEntity(id = noteCId, title = "Updated note C", content = "This is the new changed content.")

        notesDao.update(updatedNote)
        notesDao.getAll().let {
            Log.d(TAG, it[0].toString())
            assert(it[0].content == updatedNote.content)
        }
    }
}