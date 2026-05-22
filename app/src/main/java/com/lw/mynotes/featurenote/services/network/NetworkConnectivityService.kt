package com.lw.mynotes.featurenote.services.network

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class NetworkConnectivityService @Inject constructor(
    scope: CoroutineScope,
    val connectivityRepository: ConnectivityRepository,
){
    private var _isConnected = MutableStateFlow(false)
    var isConnected: StateFlow<Boolean> = _isConnected

    private val _connectionStatus = MutableStateFlow(ConnectivityObserver.Status.UNAVAILABLE)
    val connectionStatus: StateFlow<ConnectivityObserver.Status> = _connectionStatus

    init {
        connectivityRepository.observe()
        scope.launch {
            connectivityRepository.connectionStatus.collect{ status ->
                _connectionStatus.update { status }
            }
        }
        scope.launch {
            connectivityRepository.isConnected.collect { isConnected ->
                _isConnected.update { isConnected }
            }
        }
    }

    companion object {
        const val TAG = "SYNC_SERV"
    }
}