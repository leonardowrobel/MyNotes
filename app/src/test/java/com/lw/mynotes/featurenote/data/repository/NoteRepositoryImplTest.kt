package com.lw.mynotes.featurenote.data.repository

import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

// TODO(wip)
class NoteRepositoryImplTest {

    private lateinit var notesRepository: NotesRepository
    private lateinit var notesDao: NotesDao

    @Before
    fun setUp() {
        notesDao = mockk<NotesDao>()
        TODO("Not yet implemented")
//        notesRepository = NotesRepositoryImpl()
    }

    @Test
    fun getAll() {
//        coEvery { notesDao.getAll() } returns
    }

    @Test
    fun get() {
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