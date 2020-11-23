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
import com.jx3box.data.net.model.global.ArticleType
import com.jx3box.databinding.ActivityListBinding
import com.jx3box.module_imagebrowser.utils.immersionbar.ImmersionBar
import com.jx3box.mvvm.base.BaseVMActivity
import com.jx3box.ui.NormalWebActivity
import com.jx3box.ui.article.ArticleAdapter
import com.jx3box.ui.article.ArticleViewModel
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
    private val params = HashMap<String, String>()
    override fun initData() {
        params["type"] = ArticleType.BBS.type
        articleViewModel.getArticleList(true, params)
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
    }

    private fun initRecycler() {
        articleAdapter.loadMoreModule.setOnLoadMoreListener {
            articleViewModel.getArticleList(false, params)
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
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }

    override fun startObserve() {
        articleViewModel.articleListState.observe(this@BbsActivity, {
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