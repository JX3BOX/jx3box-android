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

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.jx3box.R
import com.jx3box.data.net.model.NormalFilterMenu
import com.jx3box.databinding.ItemNormalFilterBinding

/**
 * @author Carey
 * @date 2020/11/24
 */
class NormalFilterAdapter :
    BaseQuickAdapter<NormalFilterMenu, BaseDataBindingHolder<ItemNormalFilterBinding>>(
        R.layout.item_normal_filter
    ) {
    override fun convert(
        holder: BaseDataBindingHolder<ItemNormalFilterBinding>,
        item: NormalFilterMenu
    ) {
        val dataBinding = holder.dataBinding
        dataBinding?.apply {
            data = item
            executePendingBindings()
        }
    }
}