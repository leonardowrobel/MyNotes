package com.lw.mynotes.featurenote.service

import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.NotesRepository
import com.lw.mynotes.featurenote.services.NotesService
import org.junit.Test

class NotesServiceTest {

    private lateinit var notesRepository: NotesRepository // TODO: mock this
    private lateinit var notesService: NotesService



    @Test
    fun insertNote_ReturnsTrue() {
        val firstNoteToInsert = Note(title = "Note A", content = "This is the note's content")

//        val firstNoteId = notesService.save(firstNoteToInsert)
//        val firstNoteToInsetWithId = firstNoteToInsert.copy(id = firstNoteId)

//        notesService.getAll().let {
//            val note = it[0]
//            Log.d(TAG, note.toString())
//            Log.d(TAG, "Note created at: " + dateFormat.format(Date(note.createdAt)))
//            assert(it.contains(firstNoteToInsetWithId))
//        }
    }

    companion object {
        const val TAG = "WORD_SERV_TEST"
    }
}