package com.lw.mynotes.featurenote.data.repository

import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import org.junit.Before
import org.junit.Test

// TODO: wip
class NoteRepositoryImplTest {

    private lateinit var notesRepository: NotesRepository
    private lateinit var notesDao: NotesDao

    @Before
    fun setUp() {
        notesRepository = NotesRepositoryImpl()
        TODO("Not yet implemented")
    }

    @Test
    fun getAll() {
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