package com.lw.mynotes.featurenote.data.repository

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