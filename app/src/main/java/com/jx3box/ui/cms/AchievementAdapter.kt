/*
 *       Copyright (C) 2021.  jx3box.com
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

import android.view.animation.DecelerateInterpolator
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.jx3box.R
import com.jx3box.data.net.model.AchievementEntity
import com.jx3box.databinding.ItemAchievementBinding
import com.jx3box.utils.gone
import com.jx3box.utils.isVisible
import com.jx3box.utils.visible

/**
 * @author Carey
 * @date 2021/1/14
 */
class AchievementAdapter :
    BaseQuickAdapter<AchievementEntity, BaseDataBindingHolder<ItemAchievementBinding>>(
        R.layout.item_achievement
    ) {

    override fun convert(
        holder: BaseDataBindingHolder<ItemAchievementBinding>,
        item: AchievementEntity
    ) {
        val dataBinding = holder.dataBinding
        dataBinding?.apply {
            data = item
            executePendingBindings()
            if (item.item != null) {
                rewardIcon.visible()
            } else {
                rewardIcon.gone()
            }
            if (item.postfixName.isNotBlank() || item.prefixName.isNotBlank()) {
                tvCJSubTitle.visible()
            } else {
                tvCJSubTitle.gone()
            }
            val seriesAchievementList = item.seriesAchievementList
            if (!seriesAchievementList.isNullOrEmpty()) {
                lineBottomLeft.visible()
                lineBottomRight.visible()
                imgPullMore.visible()
                val layoutManager = LinearLayoutManager(context)
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                recyclerChild.layoutManager = layoutManager
                val seriesAdapter = SeriesAchievementAdapter()
                recyclerChild.adapter = seriesAdapter
                for (index in seriesAchievementList.indices) {
                    seriesAchievementList[index].isChecked = false
                }
                seriesAchievementList[0].isChecked = true
                item.seriesId = null
                seriesAdapter.setList(seriesAchievementList)
                imgPullMore.rotation = 0f
                imgPullMore.setOnClickListener {
                    if (recyclerChild.isVisible) {
                        recyclerChild.gone()
                        ViewCompat.animate(imgPullMore).setDuration(200)
                            .setInterpolator(DecelerateInterpolator())
                            .rotation(0f)
                            .start()
                    } else {
                        recyclerChild.visible()
                        ViewCompat.animate(imgPullMore).setDuration(200)
                            .setInterpolator(DecelerateInterpolator())
                            .rotation(180f)
                            .start()
                    }

                }
                seriesAdapter.setOnItemClickListener { _, _, position ->
                    for (index in seriesAchievementList.indices) {
                        seriesAchievementList[index].isChecked = false
                    }
                    seriesAchievementList[position].isChecked = true
                    item.seriesId = position
                    tvName.text = seriesAchievementList[position].name
                    tvDesc.text = seriesAchievementList[position].shortDesc
                    seriesAdapter.notifyDataSetChanged()
                }
            } else {
                recyclerChild.gone()
                lineBottomLeft.gone()
                lineBottomRight.gone()
                imgPullMore.gone()
            }
        }
    }
}