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

package com.jx3box.ui.search

import com.jx3box.R
import com.jx3box.databinding.ActivitySearchBinding
import com.jx3box.module_imagebrowser.utils.immersionbar.ImmersionBar
import com.jx3box.mvvm.base.BaseVMActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Carey
 * @date 2020/10/30
 */
class SearchActivity : BaseVMActivity() {
    private val searchViewModel by viewModel<SearchViewModel>()
    private val binding by binding<ActivitySearchBinding>(R.layout.activity_search)
    override fun initData() {
    }

    override fun initView() {
        binding.run {
            viewModel = searchViewModel
        }
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }

    override fun startObserve() {
    }
}