package com.lw.mynotes.featurenote.services.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe(): Flow<Status>

    enum class Status {
        AVAILABLE,
        LOST,
        LOSING,
        UNAVAILABLE
    }
}