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

package com.jx3box.ui.message

import com.jx3box.R
import com.jx3box.data.net.model.MessageResult
import com.jx3box.module_imagebrowser.utils.immersionbar.ImmersionBar
import com.jx3box.mvvm.base.BaseActivity
import com.jx3box.utils.DateUtil
import com.jx3box.utils.getCompatString
import kotlinx.android.synthetic.main.activity_message_detail.*
import kotlinx.android.synthetic.main.layout_title_back.*

/**
 * @author Carey
 * @date 2020/12/22
 */
class MessageDetailActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.activity_message_detail

    override fun initData() {
    }

    override fun initView() {
        val message = intent.getParcelableExtra<MessageResult>("message")
        if (message != null) {
            tvTime.text =
                DateUtil.formatDataByTimestamp(DateUtil.YMD_HMS_FORMAT, message.created.toLong())
            tvMessage.text = message.content
        }
        mImgBack.setOnClickListener { onBackPressed() }
        mTvTitle.text = getCompatString(R.string.message_detail)
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }
}