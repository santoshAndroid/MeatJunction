package com.meatjunction.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meatjunction.network.ApiHelper

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        throw IllegalArgumentException("Unknown class name")
    }
}