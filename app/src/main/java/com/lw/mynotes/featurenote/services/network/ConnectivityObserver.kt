package com.lw.mynotes.featurenote.services.network

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe()
    fun stopObserving()

    enum class Status {
        AVAILABLE,
        LOST,
        LOSING,
        UNAVAILABLE
    }
}