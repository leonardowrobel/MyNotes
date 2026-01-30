package com.lw.mynotes.featurenote.data.repository

import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.domain.repository.NoteRepository

class NoteRepositoryImpl(private val dao: NotesDao): NoteRepository {
    override suspend fun getAll(): List<NoteEntity> {
        return dao.getAll()
    }

    override suspend fun get(id: Long): NoteEntity? {
        return dao.get(id)
    }

    override suspend fun insert(note: NoteEntity) {
        dao.insert(note)
    }

    override suspend fun update(note: NoteEntity) {
        dao.update(note)
    }

    override suspend fun delete(note: NoteEntity) {
        dao.delete(note)
    }
}