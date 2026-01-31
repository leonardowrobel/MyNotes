package com.lw.mynotes.repository

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.lw.mynotes.NotesDaoTest
import com.lw.mynotes.featurenote.data.data_source.MyNotesDatabase
import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.data.repository.NoteRepositoryImpl
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@SmallTest
class WordRepositoryTest {

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

    @Test
    fun insertNote_returnsTrue() = runBlocking{
        val firstNoteToInsert = Note(title = "Note A", content = "This is the note's content")

        val firstNoteId = notesRepository.insert(NoteEntity.from(firstNoteToInsert))
        val firstNoteToInsetWithId = firstNoteToInsert.copy(id = firstNoteId)

        notesRepository.getAll().let {
            val note = it[0]
            Log.d(TAG, note.toString())
//            Log.d(TAG, "Note created at: " + dateFormat.format(Date(note.createdAt)))
            assert(it.contains(NoteEntity.from(firstNoteToInsetWithId)))
        }


    }

    companion object {
        const val TAG = "WORD_REPO_TEST"
    }
}