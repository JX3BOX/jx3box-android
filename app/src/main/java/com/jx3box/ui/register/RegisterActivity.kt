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

package com.jx3box.ui.register

import com.jx3box.R
import com.jx3box.databinding.ActivityRegisterBinding
import com.jx3box.module_imagebrowser.utils.immersionbar.ImmersionBar
import com.jx3box.mvvm.base.BaseVMActivity
import kotlinx.android.synthetic.main.layout_title_back_text.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Carey
 * @date 2020/9/30
 */
class RegisterActivity : BaseVMActivity() {
    private val registerViewModel by viewModel<RegisterViewModel>()
    private val binding by binding<ActivityRegisterBinding>(R.layout.activity_register)
    override fun initData() {
    }

    override fun initView() {
        binding.run {
            viewModel = registerViewModel
        }
        binding.mTitle.mTvTitle.text = getString(R.string.register)
        binding.mTitle.mImgBack.setOnClickListener { finish() }
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }

    override fun startObserve() {
        registerViewModel.apply {
            uiState.observe(this@RegisterActivity, {
                if (it.isLoading) showLoadingDialog(this@RegisterActivity)

                it.isSuccess?.let {
                    hideLoadingDialog()
                    showToast(R.string.register_success)
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