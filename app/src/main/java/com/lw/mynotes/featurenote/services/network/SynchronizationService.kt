package com.lw.mynotes.featurenote.services.network

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SynchronizationService @Inject constructor(
    scope: CoroutineScope,
    val connectivityRepository: ConnectivityRepository,
){
    var isConnected = connectivityRepository.isConnected
//    lateinit var connectionStatus: StateFlow<ConnectivityObserver.Status>

    private val _connectionStatus = MutableStateFlow(ConnectivityObserver.Status.UNAVAILABLE)
    val connectionStatus: StateFlow<ConnectivityObserver.Status> = _connectionStatus

    init {
        scope.launch {
            connectivityRepository.connectionStatus.collect{ status ->
                _connectionStatus.update {
                    status
                }
            }
        }
    }

    companion object {
        const val TAG = "SYNC_SERV"
    }
}