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
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.carey.module_banner.IndicatorView
import com.google.android.material.tabs.TabLayoutMediator
import com.gyf.immersionbar.ImmersionBar
import com.jx3box.R
import com.jx3box.data.net.model.global.ArticleType
import com.jx3box.mvvm.base.BaseFragment
import com.jx3box.view.BannerAdapter
import kotlinx.android.synthetic.main.fragment_bbs.*

/**
 * 茶馆
 * @author Carey
 * @date 2020/9/23
 */
class BbsFragment : BaseFragment() {

    private val imgStr = listOf(
        "https://oss.jx3box.com/upload/post/2020/9/28/4679584.png",
        "https://oss.jx3box.com/upload/post/2020/9/25/3102215.png",
        "https://oss.jx3box.com/upload/post/2020/9/25/8989395.png",
        "https://oss.jx3box.com/upload/post/2020/9/23/6653566.png",
        "https://oss.jx3box.com/upload/post/2020/9/23/1537595.png",
    )
    override val layoutId: Int
        get() = R.layout.fragment_bbs

    override fun initView() {
        initBanner()
        initViewPager()
    }

    private fun initViewPager() {
        viewpager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = ArticleType.values().size

            override fun createFragment(position: Int) =
                ArticleFragment.newInstance(ArticleType.values()[position].type)
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
        val imageAdapter = BannerAdapter()
        imageAdapter.setOnItemClickListener { _, _, _ -> showToast(banner.currentPager.toString()) }
        imageAdapter.setList(imgStr)
        banner.adapter = imageAdapter
    }

    override fun initData() {

    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.black)
            .init()
    }
}