/*
 *    Copyright (c) 2020. jx3box.com
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.jx3box.ui

import android.content.Intent
import android.graphics.Bitmap
import android.view.KeyEvent
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.carey.module_webview.ByWebTools
import com.carey.module_webview.ByWebView
import com.carey.module_webview.OnByWebClientCallback
import com.carey.module_webview.OnTitleProgressCallback
import com.jx3box.R
import com.jx3box.module_imagebrowser.utils.immersionbar.ImmersionBar
import com.jx3box.module_log.LogUtils
import com.jx3box.mvvm.base.BaseActivity
import com.jx3box.utils.getCookies
import com.jx3box.view.webview.HybridInterface
import kotlinx.android.synthetic.main.activity_normal_webview.*
import kotlinx.android.synthetic.main.layout_title_back_text.*


/**
 *@author Carey
 *@date  2020/10/1
 */
class NormalWebActivity : BaseActivity() {
    private lateinit var mWebView: ByWebView
    private var mTitle: String? = null
    private var mUrl: String? = null

    override val layoutId: Int
        get() = R.layout.activity_normal_webview

    override fun initData() {}

    override fun initView() {
        getIntentData()
        mWebView = ByWebView
            .with(this)
            .setCookie(getCookies())
            .useWebProgress(false)
            .setWebParent(ll_container, LinearLayout.LayoutParams(-1, -1))
            .useWebProgress(ContextCompat.getColor(this, R.color.colorPrimary))
            .setOnTitleProgressCallback(onTitleProgressCallback)
            .setOnByWebClientCallback(onByWebClientCallback)
            .addJavascriptInterface("hybridInject", HybridInterface(this))
            .loadUrl(mUrl)
        mImgBack.setOnClickListener { handleFinish() }
    }

    private fun getIntentData() {
        mUrl = intent.getStringExtra("url")
        mTitle = intent.getStringExtra("title")
        mTvTitle.text = mTitle
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }

    private val onTitleProgressCallback: OnTitleProgressCallback =
        object : OnTitleProgressCallback() {
            override fun onReceivedTitle(title: String) {
                LogUtils.logD("---title$title")
                if (mTitle == null)
                    mTvTitle.text = title
            }
        }

    private val onByWebClientCallback: OnByWebClientCallback = object : OnByWebClientCallback() {

        override fun isOpenThirdApp(url: String?): Boolean {
            // 处理三方链接
            LogUtils.logD("---url$url")
            hideLoadingDialog()
            return ByWebTools.handleThirdApp(this@NormalWebActivity, url)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            if (url != null) {
                if (url.contains("https://jx3.xoyo.com/")) {
                    mWebView.loadJsHolder.loadJs(
                        "javascript:" +
                                "document.getElementsByClassName('xfe-group-21')[0].style.display=\"none\";" +
                                "document.getElementsByClassName('xfe-group-1')[0].style.display=\"none\";" +
                                "var obj=document.getElementsByClassName('public_footer')[0]; " +
                                "obj.innerHTML = \"\";" +
                                "var parentObj = obj.parentNode;parentObj.removeChild(obj);"
                    )
                }
            }
            hideLoadingDialog()
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            showLoadingDialog(this@NormalWebActivity)
        }
    }

    /**
     * 上传图片之后的回调
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        mWebView.handleFileChooser(requestCode, resultCode, intent)
    }

    private fun handleFinish() {
        supportFinishAfterTransition()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (mWebView.handleKeyEvent(keyCode, event)) {
            true
        } else {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                handleFinish()
            }
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onPause() {
        super.onPause()
        mWebView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mWebView.onResume()
    }

    override fun onDestroy() {
        mWebView.onDestroy()
        super.onDestroy()
    }
}