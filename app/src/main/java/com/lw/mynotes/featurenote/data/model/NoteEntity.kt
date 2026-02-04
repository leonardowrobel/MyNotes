package com.lw.mynotes.featurenote.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lw.mynotes.featurenote.domain.model.Note

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val content: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),
    // TODO: implement soft delete
    @ColumnInfo(name = "deleted_at")
    val deletedAt: Long? = null
){
    companion object {
        fun from(note: Note): NoteEntity{
            return NoteEntity(
                note.id,
                note.title,
                note.content,
                note.createdAt,
                note.updatedAt,
                note.deletedAt)
        }
    }

    fun toNote(): Note {
        return Note(id, title, content, createdAt, updatedAt, deletedAt)
    }
}