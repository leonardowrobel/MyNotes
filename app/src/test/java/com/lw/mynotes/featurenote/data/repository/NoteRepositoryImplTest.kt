package com.lw.mynotes.featurenote.data.repository

import com.lw.mynotes.featurenote.NotesTestingUtils
import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

// TODO(wip)
class NoteRepositoryImplTest {

    private val allNotesQtd = (11..99).random()

    private val notesTestingUtils = NotesTestingUtils()

    private lateinit var notesRepository: NotesRepository
    @MockK
    lateinit var notesDao: NotesDao

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        notesRepository = NotesRepositoryImpl(notesDao)
    }

    // Basic functions/cases
    @Test
    fun get_all_notes() {
        val mockAllNotes = notesTestingUtils.createNoteEntities(allNotesQtd)
        coEvery { notesDao.getAll() } returns mockAllNotes

        val actualAllNotes = runBlocking {
            notesRepository.getAll()
        }

        assert(mockAllNotes == actualAllNotes)
    }

    @Test
    fun get_note() {
        val mockNoteEntity = notesTestingUtils.createNoteEntityWithId()
        coEvery { notesDao.get(ofType<Long>()) } returns mockNoteEntity

        val actualNoteEntity = runBlocking {
            notesRepository.get(mockNoteEntity.id)
        }

        assert(mockNoteEntity == actualNoteEntity)
    }

    @Test
    fun insert_note_then_get_it() {
        val mockNoteEntity = notesTestingUtils.createNoteEntity()
        val mockNoteEntityId = ((0..999).random()).toLong()
        coEvery { notesDao.insert(ofType<NoteEntity>()) } returns mockNoteEntityId
        coEvery { notesDao.get(mockNoteEntityId) } returns mockNoteEntity.copy(id = mockNoteEntityId)

        val actualNoteEntityId = runBlocking {
            notesRepository.insert(mockNoteEntity)
        } ?: throw IllegalStateException("Insert operation returned null")

        val actualNoteEntity = runBlocking {
            notesRepository.get(actualNoteEntityId)
        } ?: throw IllegalStateException("Get operation returned null")

        assert(mockNoteEntity == actualNoteEntity)
        assert(mockNoteEntityId == actualNoteEntityId)
    }

//    @Test
//    fun update() {
//    }

//    @Test
//    fun delete() {
//    }
}