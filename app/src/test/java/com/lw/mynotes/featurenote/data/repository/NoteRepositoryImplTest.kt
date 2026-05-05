package com.lw.mynotes.featurenote.data.repository

import com.lw.mynotes.featurenote.NotesTestingUtils
import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

// TODO(wip)
class NoteRepositoryImplTest {

    private val allNotesQtd = 11

    private val notesTestingUtils = NotesTestingUtils()

    private lateinit var notesRepository: NotesRepository
    @MockK
    lateinit var notesDao: NotesDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        notesRepository = NotesRepositoryImpl(notesDao)
    }

    @Test
    fun getAll() {
        val mockAllNotes = notesTestingUtils.createNoteEntities(allNotesQtd)
        coEvery { notesDao.getAll() } returns mockAllNotes

        val actualAllNotes = runBlocking {
            notesRepository.getAll()
        }

        assert(mockAllNotes == actualAllNotes)
    }

    @Test
    fun get() {
        val mockNoteEntity = notesTestingUtils.createNoteEntityWithId()
        coEvery { notesDao.get(ofType<Long>()) } returns mockNoteEntity

        val actualNoteEntity = runBlocking {
            notesRepository.get(mockNoteEntity.id)
        }

        assert(mockNoteEntity == actualNoteEntity)
    }

    @Test
    fun insert() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }
}