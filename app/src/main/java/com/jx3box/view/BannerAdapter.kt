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

package com.jx3box.view

import android.widget.ImageView
import com.carey.module_glidelib.EasyGlide.loadImage
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jx3box.App
import com.jx3box.R
import com.jx3box.data.net.model.IndexResult

/**
 *@author Carey
 *@date  2020/10/4
 */
class BannerAdapter : BaseQuickAdapter<IndexResult, BaseViewHolder>(R.layout.item_banner_image) {

    //回调出正确的position
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnClickListener { setOnItemClick(holder.itemView, position) }
    }

    override fun convert(holder: BaseViewHolder, item: IndexResult) {
        holder.getView<ImageView>(R.id.bannerImg).loadImage(App.CONTEXT, item.img)
    }
}