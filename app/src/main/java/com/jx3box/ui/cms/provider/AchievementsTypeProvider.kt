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

package com.jx3box.ui.cms.provider

import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import androidx.core.view.ViewCompat
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jx3box.R
import com.jx3box.data.net.model.cj.AchievementsTypeEntity
import com.jx3box.utils.getCompatString
import com.jx3box.view.BaseExpandNodeClickProvider

/**
 * @author Carey
 * @date 2020/12/24
 */
class AchievementsTypeProvider : BaseExpandNodeClickProvider<AchievementsTypeEntity>() {
    override val itemViewType: Int
        get() = 1
    override val layoutId: Int
        get() = R.layout.item_achievements_type


    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val entity: AchievementsTypeEntity = item as AchievementsTypeEntity
        helper.setText(R.id.tvTypeName, entity.name)
        helper.setText(
            R.id.tvColumnNum,
            context.getCompatString(
                R.string.cj_column_num,
                entity.achievements_count
            )
        )
        helper.setImageResource(R.id.imgExpand, R.drawable.icon_next)

        setArrowSpin(helper, item, true)
    }

    private fun setArrowSpin(helper: BaseViewHolder, data: BaseNode, isAnimate: Boolean) {
        val entity = data as AchievementsTypeEntity
        val imageView = helper.getView<ImageView>(R.id.imgExpand)
        if (entity.isExpanded) {
            if (isAnimate) {
                ViewCompat.animate(imageView).setDuration(200)
                    .setInterpolator(DecelerateInterpolator())
                    .rotation(90f)
                    .start()
            } else {
                imageView.rotation = 90f
            }
        } else {
            if (isAnimate) {
                ViewCompat.animate(imageView).setDuration(200)
                    .setInterpolator(DecelerateInterpolator())
                    .rotation(0f)
                    .start()
            } else {
                imageView.rotation = 0f
            }
        }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        val entity = data as AchievementsTypeEntity
        if (entity.own_achievements_count > 0)
            mItemClickListener?.onTypeItemClick(entity)

        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）
        getAdapter()!!.expandOrCollapse(position)
    }
}