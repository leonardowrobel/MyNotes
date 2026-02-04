package com.lw.mynotes.featurenote.data.data_source.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.lw.mynotes.featurenote.data.model.Word
import kotlinx.coroutines.flow.Flow

// TODO: Remove this
// For test porpoises only
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