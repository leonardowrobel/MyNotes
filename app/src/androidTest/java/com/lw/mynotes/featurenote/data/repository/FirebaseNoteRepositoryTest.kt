package com.lw.mynotes.featurenote.data.repository

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestoreSettings
import com.lw.mynotes.featurenote.data.data_source.dao.NotesDaoTest
import com.lw.mynotes.featurenote.data.data_source.MyNotesDatabase
import com.lw.mynotes.featurenote.data.data_source.dao.NotesDao
import com.lw.mynotes.featurenote.data.model.NoteEntity
import com.lw.mynotes.featurenote.data.repository.FirestoreNoteRepositoryImpl
import com.lw.mynotes.featurenote.data.repository.NoteRepositoryImpl
import com.lw.mynotes.featurenote.domain.model.Note
import com.lw.mynotes.featurenote.domain.repository.FirestoreNoteRepository
import com.lw.mynotes.featurenote.domain.repository.NoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.FileReader
import java.util.Date
import java.util.Properties
import javax.inject.Inject

// TODO
//@RunWith(AndroidJUnit4::class)
//@SmallTest
class FirebaseNoteRepositoryTest {

//    private lateinit var firebaseNotesRepository: FirestoreNoteRepository

//    @Before
//    fun setupDatabase(){
//        val properties = Properties()
//        val emulatorFirestorePort = 8080
//        try {
//            val reader = FileReader("config.properties")
//            properties.load(reader)
//            emulatorFirestorePort = properties.getProperty("emulator.firestore.port")
//        } catch (e: Exception){
//            e.printStackTrace()
//        }
//        val db = FirebaseFirestore.getInstance()
//        db.useEmulator("10.0.2.2", emulatorFirestorePort)
//        db.firestoreSettings = firestoreSettings {
//            isPersistenceEnabled = false
//        }
//        firebaseNotesRepository = FirestoreNoteRepositoryImpl(db)
//    }

//    @After
//    fun closeDatabase(){
//    }

//    @Test
//    fun insertNote_noUser_returnsTrue() = runBlocking{
//        val firstNoteToInsert = Note(title = "Note A", content = "This is the note's content")
//        runBlocking {
//            firebaseNotesRepository.insert(firstNoteToInsert)
//        }
//    }

    companion object {
        const val TAG = "FIRESTORE_REPO_TEST"
    }
}