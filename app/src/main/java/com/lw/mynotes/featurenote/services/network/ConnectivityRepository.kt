package com.lw.mynotes.featurenote.services.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow

class ConnectivityRepository(
    context: Context
): ConnectivityObserver {

    private val networkRequest = NetworkRequest.Builder() // Object describing a network that the application is interested in
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    //        val request = NetworkRequest.Builder()
//            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//            .build()

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _isConnected = MutableStateFlow(false)
    val isConnected: Flow<Boolean> = _isConnected

    private val _connectionStatus = MutableStateFlow(ConnectivityObserver.Status.UNAVAILABLE)
    val connectionStatus: StateFlow<ConnectivityObserver.Status> = _connectionStatus

    val networkCallback = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            _isConnected.tryEmit(true)
            _connectionStatus.tryEmit(ConnectivityObserver.Status.AVAILABLE)
        }
        override fun onLost(network: Network) {
            super.onLost(network)
            _isConnected.tryEmit(false)
            _connectionStatus.tryEmit(ConnectivityObserver.Status.LOST)
        }
        override fun onLosing(network: Network, maxMsToLive: Int) {
            super.onLosing(network, maxMsToLive)
            _isConnected.tryEmit(false)
            _connectionStatus.tryEmit(ConnectivityObserver.Status.LOSING)
        }
        override fun onUnavailable() {
            super.onUnavailable()
            _isConnected.tryEmit(false)
            _connectionStatus.tryEmit(ConnectivityObserver.Status.UNAVAILABLE)
        }
    }

    override fun observe() {
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    // TODO: find some way to programmatically call this properly
    override fun stopObserving() {
            connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    companion object {
        const val TAG = "CONN_REPO"
    }

}