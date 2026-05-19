package com.lw.mynotes.featurenote.data.services.network

import android.content.Context
//import android.net.wifi.WifiManager
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.lw.mynotes.featurenote.services.network.NetworkMonitor
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

// TODO: WIP
@RunWith(AndroidJUnit4::class)
@SmallTest
class NetworkMonitorTest {

    private lateinit var networkMonitor: NetworkMonitor
    private val context: Context = ApplicationProvider.getApplicationContext()
//    private val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager

    @Before
    fun setup(){
        networkMonitor = NetworkMonitor(context)
    }

    @Test
    fun getNetworkAvailability_ReturnTrue(){
        assert(networkMonitor.isNetworkAvailable())
    }

    // TODO: Test offline scenarios
}