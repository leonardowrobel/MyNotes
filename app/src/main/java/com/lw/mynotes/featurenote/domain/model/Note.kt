package com.lw.mynotes.featurenote.domain.model

data class Note(
    val id: Long = 0,
    val userId: String = "",
    val title: String = "",
    val content: String = "",
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val lastSyncAt: Long? = null,
    val deletedAt: Long? = null
)