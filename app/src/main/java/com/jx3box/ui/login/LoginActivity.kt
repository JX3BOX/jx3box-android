/*
 *       Copyright (C) 2020.  jx3box.com
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

package com.jx3box.ui.login

import android.webkit.WebView
import android.widget.LinearLayout
import com.carey.module_webview.ByWebView
import com.carey.module_webview.OnByWebClientCallback
import com.gyf.immersionbar.ImmersionBar
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jx3box.R
import com.jx3box.data.db.BoxDatabase
import com.jx3box.data.net.model.UserInfoResult
import com.jx3box.databinding.ActivityLoginBinding
import com.jx3box.mvvm.base.BaseVMActivity
import com.jx3box.ui.main.MainActivity
import com.jx3box.ui.register.RegisterActivity
import com.jx3box.utils.getSpValue
import com.jx3box.utils.putSpValue
import com.jx3box.utils.startKtxActivity
import kotlinx.android.synthetic.main.activity_normal_webview.*
import kotlinx.android.synthetic.main.layout_title_back_text.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * @author Carey
 * @date 2020/9/21
 */
class LoginActivity : BaseVMActivity() {
    private var mWebView: ByWebView? = null
    private val loginViewModel by viewModel<LoginViewModel>()
    private val binding by binding<ActivityLoginBinding>(R.layout.activity_login)

    override fun initData() {
    }

    override fun initView() {
        binding.run {
            viewModel = loginViewModel
        }
        binding.mTitle.mTvTitle.text = getString(R.string.login)
        binding.mTitle.mTvRightText.text = getString(R.string.register)
        binding.mTitle.mImgBack.setOnClickListener { finish() }
        binding.mTitle.mTvRightText.setOnClickListener { startKtxActivity<RegisterActivity>() }
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }

    override fun startObserve() {
        loginViewModel.apply {
            uiState.observe(this@LoginActivity, {
                if (it.isLoading) showLoadingDialog(this@LoginActivity)

                it.isSuccess?.let { userInfo ->
                    setWebViewLocalStorage(userInfo)
                }

                it.isError?.let { err ->
                    hideLoadingDialog()
                    showToast(err)
                }
            })
        }
    }

    private fun setWebViewLocalStorage(userInfo: UserInfoResult) {
        mWebView = ByWebView
            .with(this@LoginActivity)
            .setWebParent(ll_container, LinearLayout.LayoutParams(-1, -1))
            .setOnByWebClientCallback(object : OnByWebClientCallback() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    if (view != null) {
                        val token = getSpValue(
                            key = "token",
                            default = ""
                        )
                        view.evaluateJavascript(
                            "window.localStorage.setItem('created_at','${System.currentTimeMillis()}');",
                            null
                        )
                        view.evaluateJavascript(
                            "window.localStorage.setItem('logged_in','${true}');",
                            null
                        )
                        view.evaluateJavascript(
                            "window.localStorage.setItem('token','${token}');",
                            null
                        )
                        view.evaluateJavascript(
                            "window.localStorage.setItem('uid','${userInfo.id}');",
                            null
                        )
                        view.evaluateJavascript(
                            "window.localStorage.setItem('group','${userInfo.userGroup}');",
                            null
                        )
                        view.evaluateJavascript(
                            "window.localStorage.setItem('name','${userInfo.displayName}');",
                            null
                        )
                        view.evaluateJavascript(
                            "window.localStorage.setItem('avatar','${userInfo.userAvatar}');",
                            null
                        )
                        view.evaluateJavascript(
                            "window.localStorage.setItem('bio','${userInfo.userBio}');",
                            null
                        )
                        view.destroy()
                    }

                    hideLoadingDialog()
                    BoxDatabase.instance.userInfoDao().insert(userInfo)
                    putSpValue("current_user", userInfo.id)
                    putSpValue("isLogin", true)
                    showToast(R.string.login_success)
                    startKtxActivity<MainActivity>()
                    LiveEventBus.get("login_succeed").post("succeed")
                    finish()
                }
            })
            .loadUrl("https://www.jx3box.com/index/")
    }

    override fun onDestroy() {
        mWebView?.onDestroy()
        super.onDestroy()
    }
}