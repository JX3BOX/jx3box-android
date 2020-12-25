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

package com.jx3box.ui.cms

import androidx.core.view.GravityCompat
import com.jx3box.R
import com.jx3box.databinding.ActivityListBinding
import com.jx3box.module_imagebrowser.utils.immersionbar.ImmersionBar
import com.jx3box.mvvm.base.BaseVMActivity
import com.jx3box.ui.article.ArticleViewModel
import kotlinx.android.synthetic.main.layout_title_back.*
import kotlinx.android.synthetic.main.view_data_filter.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Carey
 * @date 2020/12/24
 */
class AchievementsListActivity : BaseVMActivity() {
    private val articleViewModel by viewModel<ArticleViewModel>()
    private val binding by binding<ActivityListBinding>(R.layout.activity_list)
    private val params = HashMap<String, String>()
    private val menu by lazy { getFromAssets() }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override fun initView() {
        binding.run {
            viewModel = articleViewModel
        }
        tvFilter.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.END)
        }
        mImgBack.setOnClickListener { onBackPressed() }
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }

    override fun startObserve() {
        TODO("Not yet implemented")
    }

    private fun getFromAssets(): String {
        return try {
            application.assets.open("cj_menu.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}