package com.meatjunction.base

import android.app.Dialog
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.meatjunction.R


abstract class BaseActivity : AppCompatActivity(), BaseView {
    private var mProgressView: View? = null
    private var mProgressCount = 0
    protected var tfRegular: Typeface? = null

    protected open fun configToolBar(toolbar: Toolbar?, titleView: TextView, title: String?) {
        configToolBar(toolbar, titleView, true, title)
    }

    protected open fun configToolBar(
        toolbar: Toolbar?,
        titleView: TextView,
        setDisplayHomeAsUpEnabled: Boolean,
        title: String?
    ) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(setDisplayHomeAsUpEnabled)
        supportActionBar!!.title = ""
        supportActionBar!!.elevation = 0f
        titleView.text = title
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(0, R.anim.slide_out_right)
    }

    override fun showProgress() {
        if (null == mProgressView) return
        mProgressCount = mProgressCount + 1
        mProgressView!!.visibility = View.VISIBLE
    }

    override fun dismissProgress() {
        if (null == mProgressView) return
        if (mProgressCount > 0) mProgressCount = mProgressCount - 1
        if (0 == mProgressCount) mProgressView!!.visibility = View.GONE
    }

    open fun showMessage(message: String?) {
        toast(message)
    }

    open fun showMessage(stringId: Int) {
        toast(stringId)
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun toast(stringId: Int) {
        Toast.makeText(this, stringId, Toast.LENGTH_SHORT).show()
    }


    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun customDialog(layout: Int): Dialog? {
        val dialog = Dialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setContentView(layout)
        val lp = WindowManager.LayoutParams()
        val window = dialog.window
        lp.copyFrom(window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
        return dialog
    }

    override fun customDialog(layout: Int, height: Int): Dialog? {
        val dialog = Dialog(this)
        dialog.setCanceledOnTouchOutside(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (dialog.window != null) {
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        dialog.setContentView(layout)
        val lp = WindowManager.LayoutParams()
        val window = dialog.window
        lp.copyFrom(window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = height
        window.attributes = lp
        return dialog
    }

    override fun showSuccessDialog(message: String?) {
        showExpenseDialog(message, null)
    }

    open fun showExpenseDialog(message: String?, ok: View.OnClickListener?) {

    }

    protected open fun goToActivity(clazz: Class<*>?) {
        if (clazz != null) {
            val intent = Intent(this, clazz)
            startActivity(intent)
        }
    }

    protected open fun goToActivity(clazz: Class<*>?, args: Bundle?) {
        if (clazz != null) {
            val intent = Intent(this, clazz)
            intent.putExtras(args!!)
            startActivity(intent)
        }
    }

    protected open fun makeFirstActivity(clazz: Class<*>?) {
        if (clazz != null) {
            val intent = Intent(this, clazz)
            intent.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}