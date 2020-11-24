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

package com.jx3box.ui.article.bbs

import android.os.Bundle
import androidx.core.view.GravityCompat
import com.jx3box.R
import com.jx3box.data.net.AppConfig
import com.jx3box.data.net.model.filter.getBbsFilterMenu
import com.jx3box.data.net.model.global.ArticleType
import com.jx3box.databinding.ActivityListBinding
import com.jx3box.module_imagebrowser.utils.immersionbar.ImmersionBar
import com.jx3box.mvvm.base.BaseVMActivity
import com.jx3box.ui.NormalWebActivity
import com.jx3box.ui.article.ArticleAdapter
import com.jx3box.ui.article.ArticleViewModel
import com.jx3box.ui.article.NormalFilterAdapter
import com.jx3box.utils.getCompatString
import com.jx3box.utils.startKtxActivity
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.layout_title_back.*
import kotlinx.android.synthetic.main.view_data_filter.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Carey
 * @date 2020/11/23
 */
class BbsActivity : BaseVMActivity() {
    private val articleViewModel by viewModel<ArticleViewModel>()
    private val binding by binding<ActivityListBinding>(R.layout.activity_list)
    private val articleAdapter by lazy { ArticleAdapter() }
    private val filterAdapter by lazy { NormalFilterAdapter() }
    private val params = HashMap<String, String>()
    override fun initData() {
        params["type"] = ArticleType.BBS.type
        getData()
    }

    override fun initView() {
        binding.run {
            viewModel = articleViewModel
        }
        tvFilter.setOnClickListener {
            binding.drawer.openDrawer(GravityCompat.END)
        }
        mTvTitle.text = getCompatString(R.string.bbs)
        mImgBack.setOnClickListener { finish() }
        initRecycler()
        initRadio()
    }

    private fun initRecycler() {
        articleAdapter.loadMoreModule.setOnLoadMoreListener {
            getData(isRefresh = false, isShowLoading = false)
        }
        recyclerData.adapter = articleAdapter
        articleAdapter.setOnItemClickListener { _, _, position ->
            val bundle = Bundle()
            val url = getCompatString(
                R.string.article_url,
                AppConfig.article_bbs,
                articleAdapter.getItem(position).post.ID
            )
            bundle.putString("url", url)
            startKtxActivity<NormalWebActivity>(extra = bundle)
        }

        recyclerDrawer.adapter = filterAdapter
        filterAdapter.setList(getBbsFilterMenu())
        filterAdapter.setOnItemClickListener { _, _, position ->
            val data = filterAdapter.data
            for (index in 0 until data.size) {
                data[index].isChecked = false
            }
            data[position].isChecked = true
            filterAdapter.notifyDataSetChanged()
            params["subtype"] = data[position].subType
            getData()
            drawer.closeDrawer(GravityCompat.END)
        }
    }

    fun initRadio() {
        rgMarkFilter.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbAll -> params["mark"] = ""
                R.id.rbNovice -> params["mark"] = "newbie"
                R.id.rbAdvanced -> params["mark"] = "advanced"
                R.id.rbChosen -> params["mark"] = "recommended"
                R.id.rbBest -> params["mark"] = "geek"
            }
            getData()
        }
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }

    private fun getData(isRefresh: Boolean = true, isShowLoading: Boolean = true) {
        if (isShowLoading) showLoadingDialog(this)
        articleViewModel.getArticleList(isRefresh, params)
    }

    override fun startObserve() {
        articleViewModel.articleListState.observe(this@BbsActivity, {
            hideLoadingDialog()
            it.isSuccess?.let { result ->
                articleAdapter.run {
                    if (it.isLoading) {
                        if (result.list.isNotEmpty()) {
                            addData(result.list)
                            articleAdapter.loadMoreModule.loadMoreComplete()
                        } else {
                            articleAdapter.loadMoreModule.loadMoreEnd()
                        }
                    } else
                        setList(result.list)
                }
            }
            it.isError?.let { msg ->
                if (it.isLoading) {
                    articleAdapter.loadMoreModule.loadMoreFail()
                }
                showToast(msg)
            }
        })
    }
}