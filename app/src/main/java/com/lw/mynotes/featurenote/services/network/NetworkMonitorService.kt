package com.lw.mynotes.featurenote.services.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

// TODO: WIP
class NetworkMonitorService(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isNetworkAvailable(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
    }

}