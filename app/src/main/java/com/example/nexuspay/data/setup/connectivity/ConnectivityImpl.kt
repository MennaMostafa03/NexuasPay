package com.example.nexuspay.data.setup.connectivity

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.annotation.RequiresPermission

class ConnectivityImpl(private val context: Context) : Connectivity {
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    override fun isOnline(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}