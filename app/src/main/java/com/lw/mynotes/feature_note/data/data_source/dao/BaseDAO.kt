package com.lw.mynotes.feature_note.data.data_source.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDAO<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T): Long

    @Insert
    fun insert(entities: List<T>): List<Long>

//    @Update
//    fun update(entity: T)

//    @Update
//    fun update(entities: List<T>)

//    @Delete
//    fun delete(entity: T)

//    @Delete
//    fun delete(entities: List<T>)
}