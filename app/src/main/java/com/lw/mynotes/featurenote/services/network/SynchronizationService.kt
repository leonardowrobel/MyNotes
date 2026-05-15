package com.lw.mynotes.featurenote.services.network

import javax.inject.Inject

class SynchronizationService @Inject constructor(
    observer: NetworkConnectivityObserver
){
    val isConnected = observer.observe()
}