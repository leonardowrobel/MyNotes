package com.lw.mynotes.featurenote.services.network

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class SynchronizationService @Inject constructor(
    observer: NetworkConnectivityObserver,
    val isConnected: Flow<ConnectivityObserver.Status> = observer.observe() // TODO: FIX-ME observe is not been called
){
//    lateinit var isConnected: Flow<ConnectivityObserver.Status>
//
//    fun start() {
//        isConnected =  observer.observe()
//    }

    companion object {
        const val TAG = "SYNC_SERV"
    }
}