package com.lw.mynotes.featurenote.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),
    // TODO: implement soft delete
    @ColumnInfo(name = "deleted_at")
    val deletedAt: Long? = null,
    val title: String,
    val content: String,
)