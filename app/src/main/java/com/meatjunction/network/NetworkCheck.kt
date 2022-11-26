package com.meatjunction.network

import java.lang.Exception
import java.net.InetAddress

class NetworkCheck {
    fun isInternetAvailable(): Boolean {
        return try {
            val ipAddress: InetAddress = InetAddress.getByName("www.google.com")
            !ipAddress.equals("")
        } catch (e: Exception) {
            false
        }
    }
}