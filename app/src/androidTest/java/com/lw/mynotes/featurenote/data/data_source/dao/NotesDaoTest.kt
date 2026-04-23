package com.lw.mynotes.featurenote.data.data_source.dao

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.lw.mynotes.featurenote.data.TestingUtils
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
    fun insertNote_getAll_contains_true() = runBlocking {
        val noteToInsert = TestingUtils.createNoteEntity()
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
        val notes = TestingUtils.createNoteEntities(3)

        notesDao.insert(notes)

        notesDao.getAll().let {
            val note = it[0]
            Log.d(TAG, note.toString())
            Log.d(TAG, "Note created at: " + dateFormat.format(Date(note.createdAt)))
            assert(it.size == 3)
        }
    }

    @Test
    fun updateNote_returnsTrue() = runBlocking {
        val noteB = NoteEntity(title = "Note B", content = "This is an unchanged content.")
        var updatedNote = NoteEntity(title = "", content = "")
        notesDao.insert(noteB)
        notesDao.getAll().let {
            updatedNote =
                it[0].copy(title = "Updated note B", content = "This is the new changed content.")
        }
        notesDao.update(updatedNote)
        notesDao.getAll().let {
            Log.d(TAG, it[0].toString())
            assert(it[0].content == updatedNote.content)
        }
    }

//    @Test
//    fun updateNoteUsingId_returnsTrue() = runBlocking {
//        val noteC = NoteEntity(title = "Note C", content = "This is an unchanged content.")
//        val noteCId = notesDao.insert(noteC)
////        val updatedNote = NoteEntity(id = noteCId, title = "Updated note C", content = "This is the new changed content.")
//
////        notesDao.update(updatedNote)
//        notesDao.getAll().let {
//            Log.d(TAG, it[0].toString())
////            assert(it[0].content == updatedNote.content)
//        }
//    }

    companion object {
        const val TAG = "NOTES_DAO_TEST"
    }

}