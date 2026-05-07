package com.lw.mynotes.featurenote.services

import com.lw.mynotes.featurenote.NotesTestingUtils
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class NotesServiceTest {

    private val allNotesQtd = (11..99).random()
    private val notesTestingUtils = NotesTestingUtils()

    private lateinit var notesService: NotesService
    @MockK
    private lateinit var notesRepository: NotesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        notesService = NotesService(notesRepository)
    }

    // Basic functions/cases
    @Test
    fun getAllNotes(){
        val mockAllNoteEntities = notesTestingUtils.createNoteEntities(allNotesQtd)
        coEvery { notesRepository.getAll() } returns mockAllNoteEntities

        val actualAllNoteEntities =  runBlocking {
            notesService.getAll()
        }

        assert(actualAllNoteEntities.size == mockAllNoteEntities.size)
    }

    @Test
    fun getById(){
        val mockNoteEntity = notesTestingUtils.createNoteEntityWithId()
        coEvery { notesRepository.get(mockNoteEntity.id) } returns mockNoteEntity

        val actualNote = runBlocking {
            notesService.getById(mockNoteEntity.id)
        }

        assert(actualNote == mockNoteEntity.toNote())
    }

    @Test
    fun create_verify_result(){
        val mockTitle = notesTestingUtils.getRandomTitle()
        val mockContent = notesTestingUtils.getRandomContent()

        val actualNote = notesService.create(mockTitle, mockContent)

        assert(actualNote is Note)
        assert(actualNote.title == mockTitle && actualNote.content == mockContent)
    }

    @Test
    fun update(){

    }

    @Test
    fun insertNote_ReturnsTrue() {


    }

}