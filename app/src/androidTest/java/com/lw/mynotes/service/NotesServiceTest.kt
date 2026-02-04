package com.lw.mynotes.service

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.lw.mynotes.featurenote.data.data_source.MyNotesDatabase
import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.data.repository.NoteRepositoryImpl
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.services.NotesService
import com.lw.mynotes.repository.WordRepositoryTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NotesServiceTest {

    private lateinit var database: MyNotesDatabase
    private lateinit var notesDao: NotesDao
    private lateinit var notesRepository: NoteRepositoryImpl
    private lateinit var notesService: NotesService

    @Before
    fun setupDatabase(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MyNotesDatabase::class.java
        ).allowMainThreadQueries().build()

        notesDao = database.notesDao
        notesRepository = NoteRepositoryImpl(notesDao)
        notesService = NotesService(notesRepository)
    }

    @After
    fun closeDatabase(){
        database.close()
    }

    @Test
    fun insertNote_ReturnsTrue() = runBlocking{
        val firstNoteToInsert = Note(title = "Note A", content = "This is the note's content")

        val firstNoteId = notesService.save(firstNoteToInsert)
        val firstNoteToInsetWithId = firstNoteToInsert.copy(id = firstNoteId)

        notesService.getAll().let {
            val note = it[0]
            Log.d(WordRepositoryTest.Companion.TAG, note.toString())
//            Log.d(TAG, "Note created at: " + dateFormat.format(Date(note.createdAt)))
            assert(it.contains(firstNoteToInsetWithId))
        }
    }

    companion object {
        const val TAG = "WORD_SERV_TEST"
    }
}