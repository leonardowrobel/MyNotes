package com.lw.mynotes

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.lw.mynotes.featurenote.data.data_source.MyNotesDatabase
import com.lw.mynotes.featurenote.data.data_source.dao.WordsDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.lw.mynotes.featurenote.data.model.Word
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import java.util.concurrent.CountDownLatch

// TODO: Remove class
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
        val job = async(Dispatchers.IO) {
            wordsDao.getAllWords().collect {
                assert(it.contains(name))
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun getAllWords_returnsTrue() = runBlocking {
        val potato = Word(id = 2, "Potato")
        val carrot = Word(id = 3, "Carrot")
        val broccoli = Word(id = 4, "Broccoli")
        wordsDao.insertWord(carrot)
        wordsDao.insertWord(potato)
        wordsDao.insertWord(broccoli)

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            wordsDao.getAllWords().collect {
                assert(it.size == 3)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun getWordByName_returnsTrue() = runBlocking {
        val goku = Word(id = 5, "Goku")
        wordsDao.insertWord(goku)

        assert(wordsDao.getWordByName("Goku") == goku)
    }

    @Test
    fun delete_returnsTrue() = runBlocking {
        val fork = Word(id = 6, "Fork")
        val knife = Word(id = 7, "Knife")
        wordsDao.insertWord(fork)
        wordsDao.insertWord(knife)

        wordsDao.delete()

        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO){
            wordsDao.getAllWords().collect {
                assert(!it.contains(fork))
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun update_returnsTrue() = runBlocking {
        val music = Word(id = 8, "Rock")
        wordsDao.insertWord(music)
        val updateMusic = Word(id = 8, "Pop")
        wordsDao.updateWord(updateMusic)

        assert(wordsDao.getWordByName("Pop") == updateMusic)
    }
}


