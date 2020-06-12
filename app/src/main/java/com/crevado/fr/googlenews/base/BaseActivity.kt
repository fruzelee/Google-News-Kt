package com.crevado.fr.googlenews.base

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.crevado.fr.googlenews.R
import com.crevado.fr.googlenews.databinding.ActivityBaseBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    private lateinit var baseBinding: ActivityBaseBinding
    lateinit var binding: T

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setSupportActionBar(baseBinding.toolbar)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding =
            DataBindingUtil.inflate(inflater, layoutResourceId, baseBinding.layoutContainer, true)
        baseBinding.ivBack.setOnClickListener { onBackPressed() }
        baseBinding.includedErrorLayout.tvErrorRefresh.setOnClickListener { errorRefreshClicked() }
    }

    private fun errorRefreshClicked() {}

    fun showToast(msg: String) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    fun showProgress() {
        baseBinding.progressBar.visibility = View.VISIBLE
    }

    fun showOverlayProgress() {
        baseBinding.overlayProgressContainer.visibility = View.VISIBLE
    }

    fun hideOverlayProgress() {
        baseBinding.overlayProgressContainer.visibility = View.GONE
    }

    fun hideProgress() {
        baseBinding.progressBar.visibility = View.GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun showData() {
        baseBinding.layoutContainer.visibility = View.VISIBLE
    }

    fun showError() {
        baseBinding.layoutError.visibility = View.VISIBLE
    }

    fun hideError() {
        baseBinding.layoutError.visibility = View.GONE
    }

    fun hideBack() {
        baseBinding.ivBack.visibility = View.GONE
    }

    fun getData() {
        baseBinding.layoutContainer.visibility = View.VISIBLE
    }

    fun setToolbarText(toolbarText: String) {
        baseBinding.tvToolbarTitle.text = toolbarText
    }

    fun setCustomToolbarText(toolbarText: String, size: Float) {
        baseBinding.tvToolbarTitle.text = toolbarText
        baseBinding.tvToolbarTitle.textSize = size
    }

    fun setToolbarVisibility(isVisible: Boolean) {
        if (!isVisible)
            baseBinding.toolbar.visibility = View.GONE
    }

}