package com.crevado.fr.googlenews.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import com.crevado.fr.googlenews.R
import com.crevado.fr.googlenews.base.BaseActivity
import com.crevado.fr.googlenews.databinding.ActivityDetailBinding
import com.crevado.fr.googlenews.util.VideoEnabledWebChromeClient
import com.crevado.fr.googlenews.util.VideoEnabledWebView

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailActivity : BaseActivity<ActivityDetailBinding>() {

    private var webView: VideoEnabledWebView? = null
    private var webChromeClient: VideoEnabledWebChromeClient? = null

    override val layoutResourceId: Int
        get() = R.layout.activity_detail

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideProgress()
        showData()
        setToolbarText("News Details")
        val url: String = intent.getStringExtra("url")

        initWebView(url)

        binding.webDetail.settings.javaScriptEnabled = true
        binding.webDetail.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        binding.webDetail.loadUrl(url)

    }

    @SuppressLint("InflateParams", "ObsoleteSdkInt")
    private fun initWebView(url: String) {
        // Initialize the VideoEnabledWebChromeClient and set event handlers
        val nonVideoLayout: View =
            findViewById(R.id.nonVideoLayout) // Your own view, read class comments
        val videoLayout =
            findViewById<ViewGroup>(R.id.videoLayout) // Your own view, read class comments
        val loadingView = layoutInflater.inflate(
            R.layout.view_loading_video,
            null
        ) // Your own view, read class comments

        binding.progressbar.visibility = View.VISIBLE
        binding.webDetail.visibility = View.GONE

        webChromeClient = object : VideoEnabledWebChromeClient(
            nonVideoLayout, videoLayout, loadingView, webView // See all available constructors...
        ) {
            // Subscribe to standard events, such as onProgressChanged()...
            override fun onProgressChanged(view: WebView, progress: Int) {
                // Your code...
                if (progress == 100) {
                    binding.progressbar.visibility = View.GONE
                    binding.webDetail.visibility = View.VISIBLE
                }
            }
        }
        (webChromeClient as VideoEnabledWebChromeClient).setOnToggledFullscreen { fullscreen -> // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
            if (fullscreen) {
                val attrs = window.attributes
                attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
                attrs.flags = attrs.flags or WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                window.attributes = attrs
                if (Build.VERSION.SDK_INT >= 14) {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE
                }
            } else {
                val attrs = window.attributes
                attrs.flags = attrs.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
                attrs.flags =
                    attrs.flags and WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON.inv()
                window.attributes = attrs
                if (Build.VERSION.SDK_INT >= 14) {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                }
            }
        }
        binding.webDetail.webChromeClient = webChromeClient
        // Call private class InsideWebViewClient
        binding.webDetail.webViewClient = InsideWebViewClient()

        binding.webDetail.loadUrl(url)
    }

    private class InsideWebViewClient : WebViewClient() {
        // Force links to be opened inside WebView and not in Default Browser
        override fun shouldOverrideUrlLoading(
            view: WebView,
            url: String
        ): Boolean {
            view.loadUrl(url)
            return true
        }
    }

}