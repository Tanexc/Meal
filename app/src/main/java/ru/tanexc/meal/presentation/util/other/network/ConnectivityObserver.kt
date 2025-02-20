package ru.tanexc.meal.presentation.util.other.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class NetworkConnectionObserver(
    private val context: Context
): ConnectionObserver  {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<NetworkStatus> = callbackFlow {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                launch { send(NetworkStatus.Connected) }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                launch { send(NetworkStatus.Lost) }
            }

            override fun onUnavailable() {
                super.onUnavailable()
                launch { send(NetworkStatus.Lost) }
            }
        }

        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkCallback)
        }
    }

}