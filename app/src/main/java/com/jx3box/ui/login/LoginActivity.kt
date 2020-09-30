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

import com.gyf.immersionbar.ImmersionBar
import com.jx3box.R
import com.jx3box.data.db.BoxDatabase
import com.jx3box.databinding.ActivityLoginBinding
import com.jx3box.mvvm.base.BaseVMActivity
import com.jx3box.ui.main.MainActivity
import com.jx3box.ui.register.RegisterActivity
import com.jx3box.utils.putSpValue
import com.jx3box.utils.startKtxActivity
import kotlinx.android.synthetic.main.layout_title_back_text.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Carey
 * @date 2020/9/21
 */
class LoginActivity : BaseVMActivity() {
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
                    hideLoadingDialog()
                    BoxDatabase.instance.userInfoDao().insert(userInfo)
                    putSpValue("current_user", userInfo.id)
                    putSpValue("isLogin", true)
                    showToast(R.string.login_success)
                    startKtxActivity<MainActivity>()
                    finish()
                }

                it.isError?.let { err ->
                    hideLoadingDialog()
                    showToast(err)
                }
            })
        }
    }
}