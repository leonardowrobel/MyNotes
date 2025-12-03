package com.lw.mynotes.feature_note.data.repository

import com.lw.mynotes.feature_note.data.data_source.dao.NotesDao
import com.lw.mynotes.feature_note.domain.model.Note
import com.lw.mynotes.feature_note.domain.repository.NoteRepository

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