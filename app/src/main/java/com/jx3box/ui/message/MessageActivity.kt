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

import android.os.Bundle
import com.jx3box.R
import com.jx3box.data.net.AppConfig
import com.jx3box.databinding.ActivityMessageBinding
import com.jx3box.module_imagebrowser.utils.immersionbar.ImmersionBar
import com.jx3box.mvvm.base.BaseVMActivity
import com.jx3box.ui.NormalWebActivity
import com.jx3box.utils.getCompatString
import com.jx3box.utils.startKtxActivity
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_message.*
import kotlinx.android.synthetic.main.layout_title_back.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Carey
 * @date 2020/12/22
 */
class MessageActivity : BaseVMActivity() {
    private val articleViewModel by viewModel<MessageViewModel>()
    private val binding by binding<ActivityMessageBinding>(R.layout.activity_message)
    private val messageAdapter by lazy { MessageAdapter() }
    override fun initData() {
        articleViewModel.getMessage(true)
    }

    override fun initView() {
        binding.run {
            adapter = messageAdapter
        }
        initRecycler()
        mImgBack.setOnClickListener { onBackPressed() }
        tvOfficialMessage.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("url", AppConfig.official_message)
            bundle.putString("title", getCompatString(R.string.message_official))
            startKtxActivity<NormalWebActivity>(extra = bundle)
        }
        mTvTitle.text = getCompatString(R.string.message_user)
    }

    private fun initRecycler() {
        messageAdapter.loadMoreModule.setOnLoadMoreListener {
            articleViewModel.getMessage(false)
        }
        messageAdapter.setOnItemClickListener { _, _, position ->
            val item = messageAdapter.getItem(position)
            if (item.read == 0) {
                item.read = 1
                messageAdapter.notifyItemChanged(position)
                articleViewModel.readMessage(item.ID.toString())
            }
            val bundle = Bundle()
            bundle.putParcelable("message", item)
            startKtxActivity<MessageDetailActivity>(extra = bundle)
        }
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.white)
            .init()
    }

    override fun startObserve() {
        articleViewModel.messageState.observe(this@MessageActivity) {
            it.isSuccess?.let { result ->
                messageAdapter.run {
                    if (it.isLoading) {
                        if (result.messages.isNotEmpty()) {
                            addData(result.messages)
                            messageAdapter.loadMoreModule.loadMoreComplete()
                        } else {
                            messageAdapter.loadMoreModule.loadMoreEnd()
                        }
                    } else
                        setList(result.messages)
                }
            }
        }
    }
}