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

import android.app.ProgressDialog
import android.view.View.GONE
import com.jx3box.R
import com.jx3box.data.db.BoxDatabase
import com.jx3box.databinding.ActivityLoginBinding
import com.jx3box.mvvm.base.BaseVMActivity
import com.jx3box.utils.putSpValue
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
        //注册功能暂时关闭
        binding.mTitle.mTvRightText.visibility = GONE
        binding.mTitle.mImgBack.setOnClickListener { finish() }
        binding.mTitle.mTvRightText.setOnClickListener { showToast(R.string.register) }
    }

    override fun initImmersionBar() {
    }

    override fun startObserve() {
        loginViewModel.apply {
            uiState.observe(this@LoginActivity, {
                if (it.isLoading) showProgressDialog()

                it.isSuccess?.let { userInfo ->
                    dismissProgressDialog()
                    BoxDatabase.instance.getUserInfoDao().insert(userInfo)
                    putSpValue("current_user", userInfo.uid)
                    showToast(R.string.login_success)
//                    finish()
                }

                it.isError?.let { err ->
                    dismissProgressDialog()
                    showToast(err)
                }
            })
        }
    }

    private var progressDialog: ProgressDialog? = null

    private fun showProgressDialog() {
        if (progressDialog == null)
            progressDialog = ProgressDialog(this)
        progressDialog?.show()
    }

    private fun dismissProgressDialog() {
        progressDialog?.dismiss()
    }
}