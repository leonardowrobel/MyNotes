package com.lw.mynotes.featurenote.data.repository

import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.data.model.Note
import com.lw.mynotes.featurenote.domain.repository.NoteRepository

class NoteRepositoryImpl(private val dao: NotesDao): NoteRepository {
    override suspend fun getAll(): List<Note> {
        return dao.getAll()
    }

    override suspend fun get(id: Long): Note? {
        return dao.get(id)
    }

    override suspend fun insert(note: Note) {
        dao.insert(note)
    }

    override suspend fun update(note: Note) {
        dao.update(note)
    }

    override suspend fun delete(note: Note) {
        dao.delete(note)
    }
}