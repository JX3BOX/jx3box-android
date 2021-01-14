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

package com.jx3box.view

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseExpandNode
import com.chad.library.adapter.base.entity.node.BaseNode

/**
 * @author Carey
 * @date 2021/1/14
 */
abstract class BaseNodeClickAdapter : BaseNodeAdapter() {
    var mItemClickListener: OnNodeClickListener? = null

    fun setItemClickListener(itemClickListener: OnNodeClickListener) {
        mItemClickListener = itemClickListener
    }

    interface OnNodeClickListener {
        fun onTypeClick(data: BaseExpandNode)
        fun onChildrenClick(data: BaseNode)
    }
}