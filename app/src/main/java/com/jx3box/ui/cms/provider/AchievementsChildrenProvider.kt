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

package com.jx3box.ui.cms.provider

import android.view.View
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jx3box.R
import com.jx3box.data.net.model.cj.AchievementsChildrenEntity
import com.jx3box.utils.getCompatString
import com.jx3box.view.BaseNodeClickProvider

/**
 * @author Carey
 * @date 2021/1/12
 */
class AchievementsChildrenProvider : BaseNodeClickProvider<AchievementsChildrenEntity>() {
    override val itemViewType: Int
        get() = 2
    override val layoutId: Int
        get() = R.layout.item_achievements_children

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val entity = item as AchievementsChildrenEntity
        helper.setText(R.id.tvTypeName, entity.name)
        helper.setText(
            R.id.tvColumnNum,
            context.getCompatString(
                R.string.cj_column_num,
                entity.achievements_count
            )
        )
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        mItemClickListener?.onChildrenClick(data as AchievementsChildrenEntity)
    }
}