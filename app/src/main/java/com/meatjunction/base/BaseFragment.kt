package com.meatjunction.base

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), BaseView {
    private var mBaseInterface: BaseView? = null
    protected var tfRegular: Typeface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mBaseInterface = context as BaseView
    }

    override fun toast(message: String?) {
        mBaseInterface!!.toast(message)
    }

    override fun toast(stringId: Int) {
        mBaseInterface!!.toast(stringId)
    }


    override fun customDialog(layout: Int, height: Int): Dialog? {
        return mBaseInterface!!.customDialog(layout, height)
    }

    override fun customDialog(layout: Int): Dialog? {
        return mBaseInterface!!.customDialog(layout)
    }

    override fun showSuccessDialog(message: String?) {
        mBaseInterface!!.showSuccessDialog(message)
    }

    override fun hideKeyboard() {
        mBaseInterface!!.hideKeyboard()
    }

    override fun showProgress() {
        mBaseInterface!!.showProgress()
    }

    override fun dismissProgress() {
        mBaseInterface!!.dismissProgress()
    }

    protected open fun goToActivity(clazz: Class<*>?) {
        if (clazz != null) {
            val intent = Intent(activity, clazz)
            startActivity(intent)
        }
    }

    protected open fun goToActivity(clazz: Class<*>?, args: Bundle?) {
        if (clazz != null) {
            val intent = Intent(activity, clazz)
            intent.putExtras(args!!)
            startActivity(intent)
        }
    }

}