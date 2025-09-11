package com.lw.mynotes.feature_note.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lw.mynotes.feature_note.domain.model.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordsDao {

    @Query("SELECT * FROM `word`")
    fun getAllWords(): Flow<List<Word>>

    @Query("SELECT * FROM `word` where id = :id")
    suspend fun getWord(id: Int): Word

    @Query("SELECT * FROM `word` where name = :name")
    suspend fun getWordByName(name: String): Word

    @Query("DELETE FROM word")
    suspend fun delete()

    @Insert
    suspend fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)
}