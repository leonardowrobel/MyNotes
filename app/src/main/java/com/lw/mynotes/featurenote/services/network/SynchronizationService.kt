package com.lw.mynotes.featurenote.services.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SynchronizationService @Inject constructor(
    val connectivityRepository: ConnectivityRepository,
){
    lateinit var isConnected: Flow<Boolean>
    lateinit var connectionStatus: Flow<ConnectivityObserver.Status>

    fun startService() {
        Log.d(TAG, "startService")
        connectionStatus =  connectivityRepository.connectionStatus
        isConnected = connectivityRepository.isConnected
    }

    companion object {
        const val TAG = "SYNC_SERV"
    }
}