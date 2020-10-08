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

package com.jx3box.ui.main.fragment.bbs

import android.graphics.Color
import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.carey.module_banner.IndicatorView
import com.google.android.material.tabs.TabLayoutMediator
import com.gyf.immersionbar.ImmersionBar
import com.jx3box.R
import com.jx3box.data.net.model.global.ArticleType
import com.jx3box.databinding.FragmentBbsBinding
import com.jx3box.mvvm.IndexViewModel
import com.jx3box.mvvm.base.BaseVMFragment
import com.jx3box.ui.NormalWebActivity
import com.jx3box.ui.main.article.ArticleFragment
import com.jx3box.utils.startKtxActivity
import com.jx3box.view.BannerAdapter
import kotlinx.android.synthetic.main.fragment_bbs.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 茶馆
 * @author Carey
 * @date 2020/9/23
 */
class BbsFragment : BaseVMFragment<FragmentBbsBinding>(R.layout.fragment_bbs) {
    private val indexViewModel by viewModel<IndexViewModel>()
    private val imageAdapter by lazy { BannerAdapter() }

    /**banner*/
    private val indexType = "slider"
    override fun initView() {
        initBanner()
        initViewPager()
    }

    private fun initViewPager() {
        viewpager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = ArticleType.values().size

            override fun createFragment(position: Int) =
                ArticleFragment.newInstance(ArticleType.values()[position])
        }

        val tabStr = resources.getStringArray(R.array.article_type)
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.text = tabStr[position]
        }.attach()
    }

    private fun initBanner() {
        banner.setIndicator(
            IndicatorView(context).setIndicatorColor(Color.GRAY).setIndicatorSelectorColor(
                Color.WHITE
            )
        )
        imageAdapter.setOnItemClickListener { _, _, position ->
            val bundle = Bundle()
            bundle.putString("url", imageAdapter.getItem(position).link)
            startKtxActivity<NormalWebActivity>(extra = bundle)
        }
        banner.adapter = imageAdapter
    }

    override fun startObserve() {
        indexViewModel.uiState.observe(viewLifecycleOwner, {
            it.isSuccess?.let { result ->
                imageAdapter.run {
                    setList(result)
                }
            }
        })
    }

    override fun initData() {
        indexViewModel.getIndex(indexType)
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.black)
            .init()
    }
}