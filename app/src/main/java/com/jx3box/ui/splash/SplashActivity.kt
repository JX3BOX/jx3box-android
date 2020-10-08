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

package com.jx3box.ui.splash

import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.ImmersionBar
import com.jx3box.R
import com.jx3box.databinding.ActivitySplashBinding
import com.jx3box.mvvm.base.BaseVMActivity
import com.jx3box.ui.main.MainActivity
import com.jx3box.utils.startKtxActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Carey
 * @date 2020/9/17
 */
class SplashActivity : BaseVMActivity() {
    private val splashViewModel by viewModel<SplashViewModel>()
    private val binding by binding<ActivitySplashBinding>(R.layout.activity_splash)
    override fun initData() {
        splashViewModel.getAdvert()
    }

    override fun initView() {
        binding.run {
            viewModel = splashViewModel
        }
        binding.mTvSkip.setOnClickListener {
            startKtxActivity<MainActivity>()
            finish()
        }
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .hideBar(BarHide.FLAG_HIDE_BAR)
            .transparentBar()
            .init()
    }

    override fun startObserve() {
    }
}