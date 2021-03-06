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

package com.jx3box.ui.article

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.jx3box.R
import com.jx3box.data.net.model.ArticleDetailResult
import com.jx3box.databinding.ItemArticleBinding

/**
 *@author Carey
 *@date  2020/10/4
 */
class ArticleAdapter :
    BaseQuickAdapter<ArticleDetailResult, BaseDataBindingHolder<ItemArticleBinding>>(
        R.layout.item_article
    ), LoadMoreModule {
    override fun convert(
        holder: BaseDataBindingHolder<ItemArticleBinding>,
        item: ArticleDetailResult
    ) {
        val dataBinding = holder.dataBinding
        dataBinding?.apply {
            data = item
            executePendingBindings()
        }
    }
}