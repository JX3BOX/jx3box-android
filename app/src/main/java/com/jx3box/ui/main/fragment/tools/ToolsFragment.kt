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

package com.jx3box.ui.main.fragment.tools

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ConvertUtils.dp2px
import com.carey.module_banner.IndicatorView
import com.carey.module_banner.ScaleInTransformer
import com.carey.module_glidelib.EasyGlide.loadRoundCornerImage
import com.jx3box.App
import com.jx3box.R
import com.jx3box.data.net.AppConfig
import com.jx3box.module_imagebrowser.utils.immersionbar.ImmersionBar
import com.jx3box.mvvm.base.BaseFragment
import com.jx3box.ui.NormalWebActivity
import com.jx3box.utils.gone
import com.jx3box.utils.startKtxActivity
import kotlinx.android.synthetic.main.fragment_tools.*
import kotlinx.android.synthetic.main.layout_title_back.*

/**
 * 工具
 * @author Carey
 * @date 2020/9/23
 */
class ToolsFragment : BaseFragment(), View.OnClickListener {
    private val imgs = listOf(
        "https://console.cnyixun.com/upload/post/2020/7/5/843163.png",
        "https://console.cnyixun.com/upload/post/2020/7/5/9722317.png"
    )
    override val layoutId: Int
        get() = R.layout.fragment_tools

    override fun initView() {
        mImgBack.gone()
        mTvTitle.text = getString(R.string.tools_title)
        banner.setAutoTurningTime(3000)
            .setIndicator(
                indicator.setIndicatorColor(Color.GRAY)
                    .setIndicatorStyle(IndicatorView.IndicatorStyle.INDICATOR_BEZIER)
                    .setIndicatorSelectorColor(Color.RED), false
            )
            .setPageMargin(dp2px(20F), dp2px(10F))
            .addPageTransformer(ScaleInTransformer())
            .adapter = ImageAdapter(imgs)
        tvMacro.setOnClickListener(this)
        tvJx3Dat.setOnClickListener(this)
        tvTools.setOnClickListener(this)
        tvBps.setOnClickListener(this)
        tvFB.setOnClickListener(this)
        tvCJ.setOnClickListener(this)
        tvNPC.setOnClickListener(this)
        tvFbSkill.setOnClickListener(this)
        tvFbDrop.setOnClickListener(this)
        tvGem.setOnClickListener(this)
        tvTeam.setOnClickListener(this)
        tvGoldPrice.setOnClickListener(this)
        tvFlowerPrice.setOnClickListener(this)
        tvThirdPz.setOnClickListener(this)
        tvThirdDps.setOnClickListener(this)
        tvThirdSpeed.setOnClickListener(this)
        tvThirdExam.setOnClickListener(this)
        tvThirdQiYu.setOnClickListener(this)
    }

    override fun initData() {

    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }

    internal class ImageAdapter(private val items: List<String>) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_banner_image, parent, false)
            return ImageViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val imageViewHolder = holder as ImageViewHolder
            imageViewHolder.image.loadRoundCornerImage(App.CONTEXT, items[position], dp2px(10F))
        }

        override fun getItemCount(): Int {
            return items.size
        }
    }

    internal class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.bannerImg)
    }

    override fun onClick(v: View?) {
        val bundle = Bundle()
        val url = when (v) {
            tvMacro -> AppConfig.article_macro
            tvBps -> AppConfig.article_bps
            tvTools -> AppConfig.article_tools
            tvJx3Dat -> AppConfig.article_jx3dat
            tvFB -> AppConfig.article_fb
            tvFbDrop -> AppConfig.fb_drop
            tvNPC -> AppConfig.fb_npc_data
            tvFbSkill -> AppConfig.fb_skill_data
            tvGem -> AppConfig.fb_gem
            tvGoldPrice -> AppConfig.gold_price
            tvFlowerPrice -> AppConfig.flower_price
            tvThirdPz -> AppConfig.third_pz
            tvThirdDps -> AppConfig.third_dps
            tvThirdSpeed -> AppConfig.third_speed
            tvThirdExam -> AppConfig.third_exam
            tvThirdQiYu -> AppConfig.third_serendipity
            else -> ""
        }
        bundle.putString("url", url)
        startKtxActivity<NormalWebActivity>(extra = bundle)
    }
}