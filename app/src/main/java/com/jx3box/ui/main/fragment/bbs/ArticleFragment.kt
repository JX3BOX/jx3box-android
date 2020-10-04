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

package com.jx3box.ui.main.fragment.bbs

import android.os.Bundle
import com.jx3box.R
import com.jx3box.databinding.FragmentArticleBinding
import com.jx3box.mvvm.base.BaseVMFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *@author Carey
 *@date  2020/10/4
 */
class ArticleFragment : BaseVMFragment<FragmentArticleBinding>(R.layout.fragment_article) {
    private val articleViewModel by viewModel<ArticleViewModel>()
    private val articleAdapter by lazy { ArticleAdapter() }
    private val articleType by lazy { arguments?.getString(ARTICLE_TYPE) }

    companion object {
        private const val ARTICLE_TYPE = "articleType"
        fun newInstance(articleType: String): ArticleFragment {
            val fragment = ArticleFragment()
            val bundle = Bundle()
            bundle.putString(ARTICLE_TYPE, articleType)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun initView() {
        binding.run {
            adapter = articleAdapter
        }
    }

    override fun initData() {
        loadData()
    }

    override fun startObserve() {
        articleViewModel.uiState.observe(viewLifecycleOwner, {
//            if (it.isLoading) showLoadingDialog(requireContext())
            it.isSuccess?.let { result ->
                articleAdapter.run {
                    setList(result.list)
                }
            }
            it.isError?.let { msg ->
//                hideLoadingDialog()
                showToast(msg)
            }
        })
    }

    override fun initImmersionBar() {}

    private fun loadData() {
        articleType?.run {
            val params = HashMap<String, String>()
            params["type"] = this
            articleViewModel.getArticleList(params = params)
        }
    }
}