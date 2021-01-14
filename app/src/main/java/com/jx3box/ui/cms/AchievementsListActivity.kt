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
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode
import com.google.gson.Gson
import com.jx3box.R
import com.jx3box.data.net.model.cj.AchievementsChildrenEntity
import com.jx3box.data.net.model.cj.AchievementsTypeEntity
import com.jx3box.databinding.ActivityListBinding
import com.jx3box.module_imagebrowser.utils.immersionbar.ImmersionBar
import com.jx3box.mvvm.base.BaseVMActivity
import com.jx3box.ui.article.ArticleViewModel
import com.jx3box.utils.fromListJson
import com.jx3box.view.BaseNodeClickAdapter
import kotlinx.android.synthetic.main.activity_list.*
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
    private val menu by lazy { Gson().fromListJson<List<AchievementsTypeEntity>>(getFromAssets()) }
    private val mMenuAdapter by lazy { AchievementsTreeAdapter() }
    private val mAdapter by lazy { AchievementAdapter() }

    override fun initData() {
        getData("1")
    }

    override fun initView() {
        with(binding) {
            viewModel = articleViewModel
        }
        tvFilter.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.END)
        }
        binding.drawer.openDrawer(GravityCompat.END)
        mImgBack.setOnClickListener { onBackPressed() }
        initDrawer()
        initRecycler()
    }

    private fun initRecycler() {
        recyclerData.adapter = mAdapter
    }

    private fun initDrawer() {
        recyclerDrawer.adapter = mMenuAdapter
        mMenuAdapter.setList(menu)
        mMenuAdapter.setItemClickListener(object : BaseNodeClickAdapter.OnNodeClickListener {
            override fun onTypeClick(data: BaseExpandNode) {
                val entity = data as AchievementsTypeEntity
                getData(entity.sub.toString())
            }

            override fun onChildrenClick(data: BaseNode) {
                val entity = data as AchievementsChildrenEntity
                getData(entity.sub.toString(), entity.detail.toString())
            }
        })
        mMenuAdapter.expand(0)
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }

    private fun getData(subId: String, detailId: String? = null) {
        showLoadingDialog(this)
        articleViewModel.getAchievementsList(subId, detailId)
    }

    override fun startObserve() {
        articleViewModel.achievementsListState.observe(this@AchievementsListActivity) {
            hideLoadingDialog()
            it.isSuccess?.run {
                mAdapter.setList(achievements)
            }
        }
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