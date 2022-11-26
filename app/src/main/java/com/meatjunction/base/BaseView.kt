package com.meatjunction.base

import android.app.Dialog

interface BaseView {
    fun showProgress()

    fun dismissProgress()

    fun toast(message: String?)

    fun toast(stringId: Int)

    fun hideKeyboard()

    fun customDialog(layout: Int, height: Int): Dialog?

    fun customDialog(layout: Int): Dialog?

    fun showSuccessDialog(message: String?)
}