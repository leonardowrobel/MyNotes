package com.lw.mynotes.featurenote.data.repository

import com.lw.mynotes.featurenote.utils.NotesTestingUtils
import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
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
    fun getAllNotes() {
        val mockAllNotes = notesTestingUtils.createNoteEntities(allNotesQtd)
        coEvery { notesDao.getAll() } returns mockAllNotes

        val actualAllNotes = runBlocking {
            notesRepository.getAll()
        }

        assert(mockAllNotes == actualAllNotes)
    }

    @Test
    fun getNote() {
        val mockNoteEntity = notesTestingUtils.createNoteEntityWithId()
        coEvery { notesDao.get(ofType<Long>()) } returns mockNoteEntity

        val actualNoteEntity = runBlocking {
            notesRepository.get(mockNoteEntity.id)
        }

        assert(mockNoteEntity == actualNoteEntity)
    }

    @Test
    fun insertNote_getIt() {
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

        assert(mockNoteEntity.copy(id = mockNoteEntityId) == actualNoteEntity)
        assert(mockNoteEntityId == actualNoteEntityId)
    }

    @Test
    fun updateNote_verify_daoUpdateCall() {
        val mockNoteEntity = notesTestingUtils.createNoteEntityWithId()
        coEvery { notesRepository.update(ofType<NoteEntity>()) } returns Unit

        runBlocking {
            notesRepository.update(mockNoteEntity)
        }

        coVerify { notesDao.update(ofType<NoteEntity>()) }
    }

    @Test
    fun deleteNote_verify_daoDeleteCall() {
        val mockNoteEntity = notesTestingUtils.createNoteEntityWithId()
        coEvery { notesRepository.delete(ofType<NoteEntity>()) } returns Unit

        runBlocking {
            notesRepository.delete(mockNoteEntity)
        }

        coVerify { notesDao.delete(ofType<NoteEntity>()) }
    }
}