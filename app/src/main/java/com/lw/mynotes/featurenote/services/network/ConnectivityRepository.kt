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
    val connectionStatus: Flow<ConnectivityObserver.Status> = _connectionStatus

    val networkCallback = object : ConnectivityManager.NetworkCallback(){
        override fun onAvailable(network: Network) {
            Log.d(TAG, "AVAILABLE")
            super.onAvailable(network)
            _isConnected.value = true
            _connectionStatus.value = ConnectivityObserver.Status.AVAILABLE
        }
        override fun onLost(network: Network) {
            Log.d(TAG, "LOST")
            super.onLost(network)
            _isConnected.value = false
            _connectionStatus.value = ConnectivityObserver.Status.LOST
        }
//        override fun onLosing(network: Network, maxMsToLive: Int) {
//            super.onLosing(network, maxMsToLive)
//            Log.d(TAG, "trySend LOSING")
//            trySend(ConnectivityObserver.Status.LOSING)
//        }
//        override fun onUnavailable() {
//            super.onUnavailable()
//            Log.d(TAG, "trySend UNAVAILABLE")
//            trySend(ConnectivityObserver.Status.UNAVAILABLE)
//        }
    }

    // TODO: FIX-ME - call register and assure the callback is been called
    override fun observe(): Flow<ConnectivityObserver.Status> = callbackFlow {
        Log.d(TAG, "observe()")

        awaitClose {
            Log.d(TAG, "awaitClose")
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

    init {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    companion object {
        const val TAG = "CONN_REPO"
    }

}