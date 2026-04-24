package com.lw.mynotes.featurenote.data.data_source.dao

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.lw.mynotes.featurenote.data.NotesTestingUtils
import com.lw.mynotes.featurenote.data.data_source.MyNotesDatabase
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

    private lateinit var database: MyNotesDatabase
    private lateinit var notesDao: NotesDao
    private lateinit var dateFormat: SimpleDateFormat
    private lateinit var notesTestingUtils: NotesTestingUtils

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
        notesTestingUtils = NotesTestingUtils()
    }

    @After
    fun closeDatabase(){
        database.close()
    }

    @Test
    fun insertNote_getAll_contains_true() = runBlocking {
        val noteToInsert = notesTestingUtils.createNoteEntity()
        val idFromDB = notesDao.insert(noteToInsert)
        val noteToInsertWithId = noteToInsert.copy(id = idFromDB)

        notesDao.getAll().let {
            val note = it[0]
            Log.d(TAG, note.toString())
            Log.d(TAG, "Note created at: " + dateFormat.format(Date(note.createdAt)))
            assert(it.contains(noteToInsertWithId))
        }
    }

    @Test
    fun insertNotes_getAll_size_true() = runBlocking {
        val notes = notesTestingUtils.createNoteEntities(3)

        notesDao.insert(notes)

        notesDao.getAll().let {
            val note = it[0]
            Log.d(TAG, note.toString())
            Log.d(TAG, "Note created at: " + dateFormat.format(Date(note.createdAt)))
            assert(it.size == 3)
        }
    }

    @Test
    fun updateNote_newContent_returnsTrue() = runBlocking {
        var noteToInsert = notesTestingUtils.createNoteEntity()
        val newTitle = "Updated title."
        val newContent = "Updated content."
        lateinit var updatedNote: NoteEntity
        noteToInsert = noteToInsert.copy(id = notesDao.insert(noteToInsert))
        notesDao.get(noteToInsert.id).let {
            updatedNote = it.copy(title = newTitle, content = newContent)
        }
        notesDao.update(updatedNote)
        notesDao.get(updatedNote.id).let {
            Log.d(TAG, it.toString())
            assert(it.content == newContent && it.title == newTitle)
        }
    }

    // TODO: tests for verifying database integrity after insertions, updates and delete

    companion object {
        const val TAG = "NOTES_DAO_TEST"
    }

}