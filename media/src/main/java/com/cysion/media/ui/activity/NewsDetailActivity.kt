package com.cysion.media.ui.activity

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import com.cysion.ktbox.base.BaseModelActivity
import com.cysion.ktbox.base.NoViewModel
import com.cysion.ktbox.utils.whiteTextTheme
import com.cysion.media.R
import com.cysion.media.constant.BUNDLE_KEY
import com.cysion.media.constant.LINK
import com.cysion.media.constant.TITLE
import com.cysion.other.color
import com.cysion.uibox.bar.TopBar
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : BaseModelActivity<NoViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_news_detail

    private val title: String by lazy {
        intent.getBundleExtra(BUNDLE_KEY)?.getString(TITLE)?:""
    }
    private val linkurl: String by lazy {
        intent.getBundleExtra(BUNDLE_KEY)?.getString(LINK)?:""
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun initView() {
        whiteTextTheme(color(R.color.colorAccent))
        topbar.apply {
            setTitle(title)
            setOnTopBarClickListener { _, pos ->
                if (pos == TopBar.Pos.LEFT) {
                    finish()
                }
            }
        }
        webview.settings.javaScriptEnabled = true
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: String?): Boolean {
                request?.let {
                    if (it.startsWith("http")) {
                        view?.loadUrl(request)
                        return true
                    }
                }
                return false

            }
        }
        webview.loadUrl(linkurl)
    }
    override fun observeModel() {

    }

    override fun onReceivedStateEvent(type: Int, msg: String) {
    }
}
