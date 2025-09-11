package com.lw.mynotes

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.lw.mynotes.feature_note.data.data_source.MyNotesDatabase
import com.lw.mynotes.feature_note.data.data_source.WordsDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.lw.mynotes.feature_note.domain.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
@SmallTest
class WordsDaoTest {
    private lateinit var database: MyNotesDatabase
    private lateinit var wordsDao: WordsDao

    @Before
    fun setupDatabase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MyNotesDatabase::class.java
        ).allowMainThreadQueries().build()

        wordsDao = database.wordsDao()
    }

    @After
    fun closeDatabase() {
        database.close()
    }

    @Test
    fun insertWord_returnsTrue() = runBlocking {
        val name = Word(id = 1, "Arnaldo")
        wordsDao.insertWord(name)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO){
            wordsDao.getAllWords().collect {
                assert(it.contains(name))
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }
}