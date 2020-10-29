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

import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.webkit.WebView
import android.widget.LinearLayout
import com.blankj.utilcode.util.NetworkUtils
import com.carey.module_webview.ByWebView
import com.carey.module_webview.OnByWebClientCallback
import com.jeremyliao.liveeventbus.LiveEventBus
import com.jx3box.App
import com.jx3box.BuildConfig
import com.jx3box.R
import com.jx3box.data.db.BoxDatabase
import com.jx3box.data.net.model.BoxEvent
import com.jx3box.data.net.model.UserInfoResult
import com.jx3box.data.net.model.global.ThirdLoginType
import com.jx3box.databinding.ActivityLoginBinding
import com.jx3box.module_imagebrowser.utils.immersionbar.ImmersionBar
import com.jx3box.mvvm.base.BaseVMActivity
import com.jx3box.third.listener.TencentUiListener
import com.jx3box.ui.main.MainActivity
import com.jx3box.ui.register.RegisterActivity
import com.jx3box.utils.getSpValue
import com.jx3box.utils.putSpValue
import com.jx3box.utils.startKtxActivity
import com.jx3box.utils.toast
import com.tencent.connect.common.Constants
import com.tencent.mm.opensdk.modelmsg.SendAuth
import com.tencent.tauth.Tencent
import kotlinx.android.synthetic.main.activity_normal_webview.*
import kotlinx.android.synthetic.main.layout_title_back_text.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * @author Carey
 * @date 2020/9/21
 */
class LoginActivity : BaseVMActivity(), View.OnClickListener {
    private lateinit var tencent: Tencent
    private lateinit var mUiListener: TencentUiListener
    private var mWebView: ByWebView? = null
    private val loginViewModel by viewModel<LoginViewModel>()
    private val binding by binding<ActivityLoginBinding>(R.layout.activity_login)

    override fun initData() {
        onEvent()
    }

    override fun initView() {
        binding.run {
            viewModel = loginViewModel
        }
        binding.mTitle.mTvTitle.text = getString(R.string.login)
        binding.mTitle.mTvRightText.text = getString(R.string.register)
        binding.mTitle.mImgBack.setOnClickListener(this)
        binding.mTitle.mTvRightText.setOnClickListener(this)
        binding.mQQLogin.setOnClickListener(this)
        binding.mWxLogin.setOnClickListener(this)
        binding.mWbLogin.setOnClickListener(this)
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

            wxState.observe(this@LoginActivity, {
                if (it.isLoading) showLoadingDialog(this@LoginActivity)
                it.isSuccess?.let { data ->
                    if (TextUtils.isEmpty(data.errcode)) {
                        val params: MutableMap<String, String> = HashMap()
                        params["access_token"] = data.access_token
                        params["openid"] = data.openid
                        loginViewModel.thirdLogin(ThirdLoginType.WECHAT.type, params)
                    } else
                        data.errmsg?.run { toast(this) }
                }
            })
        }
    }

    /**
     * qq授权
     */
    private fun qqOAuth() {
        if (NetworkUtils.isConnected()) {
            tencent = Tencent.createInstance(BuildConfig.QQ_KEY, App.CONTEXT)
            if (tencent.isQQInstalled(this)) {
                mUiListener = TencentUiListener()
                tencent.login(this, "all", mUiListener)
            } else {
                toast(resources.getString(R.string.qq_uninstall))
            }
        } else {
            toast(resources.getString(R.string.network_error))
        }

    }

    /**
     * 微信授权
     */
    private fun wxOAuth() {
        if (NetworkUtils.isConnected()) {
            if (App.WXAPI.isWXAppInstalled) {
                val req = SendAuth.Req()
                req.scope = "snsapi_userinfo"
                req.state = "none"
                App.WXAPI.sendReq(req)
            } else {
                toast(resources.getString(R.string.wechat_uninstall))
            }
        } else {
            toast(resources.getString(R.string.network_error))
        }
    }

    /**
     * 微博授权
     */
    private fun wbOAuth() {

    }

    override fun onClick(v: View?) {
        when (v) {
            binding.mQQLogin -> qqOAuth()
            binding.mWxLogin -> wxOAuth()
            binding.mWbLogin -> wbOAuth()
            binding.mTitle.mImgBack -> finish()
            binding.mTitle.mTvRightText -> startKtxActivity<RegisterActivity>()
        }
    }

    override fun onDestroy() {
        mWebView?.onDestroy()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //腾讯QQ回调
        Tencent.onActivityResultData(requestCode, resultCode, data, mUiListener)
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, mUiListener)
            }
        }
    }

    /**
     * 消息订阅事件
     */
    private fun onEvent() {
        LiveEventBus
            .get(BoxEvent::class.java)
            .observe(this, {
                it?.run {
                    when (key) {
                        BoxEvent.WX_AUTH_SUCCESS -> loginViewModel.getWxToken(value)

                        BoxEvent.QQ_AUTH_SUCCESS -> {
                            val params: MutableMap<String, String> = HashMap()
                            params["access_token"] = value
                            loginViewModel.thirdLogin(ThirdLoginType.QQ.type, params)
                        }
                    }
                }
            })
    }

    /**
     * 设置webview缓存数据
     */
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


}