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

package com.jx3box.ui.main.article

import android.webkit.WebView
import android.widget.FrameLayout
import com.carey.module_webview.ByWebView
import com.carey.module_webview.OnByWebClientCallback
import com.gyf.immersionbar.ImmersionBar
import com.jx3box.R
import com.jx3box.data.net.AppConfig
import com.jx3box.databinding.ActivityArticleDetailBinding
import com.jx3box.mvvm.base.BaseVMActivity
import com.jx3box.utils.getCookies
import com.jx3box.view.webview.CustomWebView
import kotlinx.android.synthetic.main.activity_article_detail.*
import kotlinx.android.synthetic.main.layout_title_back_text.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author Carey
 *@date  2020/10/7
 */
class ArticleDetailActivity : BaseVMActivity() {
    private lateinit var mWebView: ByWebView
    private val articleViewModel by viewModel<ArticleViewModel>()
    private val binding by binding<ActivityArticleDetailBinding>(R.layout.activity_article_detail)
    private var articleId = -1

    override fun initData() {
        articleViewModel.getArticleDetail(articleId.toString())
    }

    override fun initView() {
        binding.run {
            viewModel = articleViewModel
        }
        articleId = intent.getIntExtra("article_id", -1)
        mTvTitle.text = intent.getStringExtra("article_type")
        mImgBack.setOnClickListener { finish() }
        initWebView()
    }

    private fun initWebView() {
        showLoadingDialog(this)
        mWebView = ByWebView
            .with(this)
            .setCustomWebView(CustomWebView(this))
            .setCookie(getCookies())
            .setWebParent(flContent, FrameLayout.LayoutParams(-1, -1))
            .useWebProgress(false)
            .setOnByWebClientCallback(onByWebClientCallback)
            .loadUrl(AppConfig.article_html + articleId)
    }

    private val onByWebClientCallback: OnByWebClientCallback = object : OnByWebClientCallback() {
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            hideLoadingDialog()
        }
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
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

    override fun startObserve() {
    }
}