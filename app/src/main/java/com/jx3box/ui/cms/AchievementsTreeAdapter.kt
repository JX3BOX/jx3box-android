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

package com.jx3box.ui.cms

import com.chad.library.adapter.base.entity.node.BaseNode
import com.jx3box.data.net.model.cj.AchievementsChildrenEntity
import com.jx3box.data.net.model.cj.AchievementsTypeEntity
import com.jx3box.ui.cms.provider.AchievementsChildrenProvider
import com.jx3box.ui.cms.provider.AchievementsTypeProvider
import com.jx3box.view.BaseExpandNodeClickProvider
import com.jx3box.view.BaseNodeClickAdapter
import com.jx3box.view.BaseNodeClickProvider

/**
 * @author Carey
 * @date 2020/12/24
 */
class AchievementsTreeAdapter : BaseNodeClickAdapter() {
    init {
        val typeProvider = AchievementsTypeProvider()
        val childrenProvider = AchievementsChildrenProvider()
        typeProvider.setItemClickListener(object :
            BaseExpandNodeClickProvider.ItemClickListener<AchievementsTypeEntity> {
            override fun onTypeItemClick(data: AchievementsTypeEntity) {
                mItemClickListener?.onTypeClick(data)
            }
        })
        childrenProvider.setItemClickListener(object :
            BaseNodeClickProvider.ItemClickListener<AchievementsChildrenEntity> {
            override fun onChildrenClick(data: AchievementsChildrenEntity) {
                mItemClickListener?.onChildrenClick(data)
            }
        })
        addNodeProvider(typeProvider)
        addNodeProvider(childrenProvider)
    }

    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        val node = data[position]
        if (node is AchievementsTypeEntity) {
            return 1
        } else if (node is AchievementsChildrenEntity) {
            return 2
        }
        return -1
    }
}