package com.meatjunction.network

import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor

class HttpLogger(private val mTag: String) : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        Log.e(mTag, message)
    }
}