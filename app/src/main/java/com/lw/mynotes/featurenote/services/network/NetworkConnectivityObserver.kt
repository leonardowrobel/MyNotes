package com.lw.mynotes.featurenote.services.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkConnectivityObserver(
    context: Context
): ConnectivityObserver {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // TODO: FIX-ME observe is not been called
    override fun observe(): Flow<ConnectivityObserver.Status> = callbackFlow {
        Log.d(TAG, "observe()")
        val callback = object : ConnectivityManager.NetworkCallback(){
            override fun onAvailable(network: Network) {
                Log.d(TAG, "trySend AVAILABLE")
                trySend(ConnectivityObserver.Status.AVAILABLE)
            }
            override fun onLost(network: Network) {
                Log.d(TAG, "trySend LOST")
                trySend(ConnectivityObserver.Status.LOST)
            }
            override fun onLosing(network: Network, maxMsToLive: Int) {
                Log.d(TAG, "trySend LOSING")
                trySend(ConnectivityObserver.Status.LOSING)
            }
            override fun onUnavailable() {
                Log.d(TAG, "trySend UNAVAILABLE")
                trySend(ConnectivityObserver.Status.UNAVAILABLE)
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(request, callback)

        awaitClose {
            Log.d(TAG, "awaitClose")
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

    companion object {
        const val TAG = "NETWORK_CONN_OBSERVER"
    }

}