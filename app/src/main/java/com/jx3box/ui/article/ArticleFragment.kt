/*
 *    Copyright (c) 2020. jx3box.com
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

package com.jx3box.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.jx3box.R
import com.jx3box.data.net.AppConfig
import com.jx3box.data.net.model.global.ArticleType
import com.jx3box.databinding.FragmentArticleBinding
import com.jx3box.mvvm.base.BaseVMFragment
import com.jx3box.ui.NormalWebActivity
import com.jx3box.utils.getCompatString
import com.jx3box.utils.startKtxActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author Carey
 *@date  2020/10/4
 */
class ArticleFragment : BaseVMFragment<FragmentArticleBinding>(R.layout.fragment_article) {
    private val articleViewModel by viewModel<ArticleViewModel>()
    private val articleAdapter by lazy { ArticleAdapter() }
    private val articleType by lazy { arguments?.getSerializable(ARTICLE_TYPE) as ArticleType }

    companion object {
        private const val ARTICLE_TYPE = "articleType"
        fun newInstance(articleType: ArticleType): ArticleFragment {
            val fragment = ArticleFragment()
            val bundle = Bundle()
            bundle.putSerializable(ARTICLE_TYPE, articleType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initView() {
        binding.run {
            adapter = articleAdapter
        }
        // 打开或关闭加载更多功能（默认为true）
        articleAdapter.loadMoreModule.isEnableLoadMore = false
        articleAdapter.setOnItemClickListener { _, _, position ->
            val bundle = Bundle()
            var url = ""
            val id = articleAdapter.getItem(position).post.ID
            when (articleType.type) {
                ArticleType.BBS.type ->
                    url = requireContext().getCompatString(
                        R.string.article_url,
                        AppConfig.article_bbs,
                        id
                    )
                ArticleType.BPS.type ->
                    url = requireContext().getCompatString(
                        R.string.article_url,
                        AppConfig.article_bps,
                        id
                    )
                ArticleType.FB.type ->
                    url = requireContext().getCompatString(
                        R.string.article_url,
                        AppConfig.article_fb,
                        id
                    )
                ArticleType.JX3DAT.type ->
                    url = requireContext().getCompatString(
                        R.string.article_url,
                        AppConfig.article_jx3dat,
                        id
                    )
                ArticleType.TOOL.type ->
                    url = requireContext().getCompatString(
                        R.string.article_url,
                        AppConfig.article_tools,
                        id
                    )
            }
            bundle.putString("url", url)
            startKtxActivity<NormalWebActivity>(extra = bundle)
        }
    }

    override fun initData() {
        loadData()
    }

    override fun startObserve() {
        articleViewModel.articleListState.observe(viewLifecycleOwner, {
            it.isSuccess?.let { result ->
                articleAdapter.run {
                    setList(result.list)
                    articleAdapter.setFooterView(footView())
                }
            }
            it.isError?.let { msg ->
                showToast(msg)
            }
        })
    }

    private fun footView(): View {
        val view: View = LayoutInflater.from(requireContext())
            .inflate(R.layout.view_see_more, binding.recyclerview, false)
        view.findViewById<TextView>(R.id.tvSeeMore).setOnClickListener {
            val bundle = Bundle()
            when (articleType.type) {
                ArticleType.BBS.type -> {
                    bundle.putString("title", requireContext().getCompatString(R.string.bbs))
                    bundle.putString("type", articleType.type)
                    startKtxActivity<NormalCmsListActivity>(extra = bundle)
                }
//                ArticleType.BPS.type -> url = AppConfig.article_bps
//                ArticleType.FB.type -> url = AppConfig.article_fb
//                ArticleType.JX3DAT.type -> url = AppConfig.article_jx3dat
                ArticleType.TOOL.type -> {
                    bundle.putString(
                        "title",
                        requireContext().getCompatString(R.string.tools_tutorials)
                    )
                    bundle.putString("type", articleType.type)
                    startKtxActivity<NormalCmsListActivity>(extra = bundle)
                }
            }
//            bundle.putString("url", url)
//            startKtxActivity<NormalWebActivity>(extra = bundle)
        }
        return view
    }

    override fun initImmersionBar() {}

    private fun loadData() {
        articleType.run {
            val params = HashMap<String, String>()
            params["type"] = this.type
            articleViewModel.getArticleList(params = params)
        }
    }
}