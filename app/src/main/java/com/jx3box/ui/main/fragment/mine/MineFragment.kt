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

package com.jx3box.ui.main.fragment.mine

import android.content.Intent
import android.net.Uri
import com.gyf.immersionbar.ImmersionBar
import com.jx3box.R
import com.jx3box.databinding.FragmentMineBinding
import com.jx3box.mvvm.base.BaseVMFragment
import kotlinx.android.synthetic.main.fragment_mine.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * 我的
 * @author Carey
 * @date 2020/9/23
 */
class MineFragment : BaseVMFragment<FragmentMineBinding>(R.layout.fragment_mine) {
    private val mineViewModel by viewModel<MineViewModel>()

    override fun startObserve() {

    }

    override fun initView() {
        binding.run {
            viewModel = mineViewModel
        }
        tvFeedBack.setOnClickListener {
            val uri = Uri.parse(getString(R.string.sendto))
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_topic))
            startActivity(intent)
        }
    }

    override fun initData() {
    }

    override fun initImmersionBar() {
        ImmersionBar.with(this)
            .titleBar(tool_bar)
            .statusBarDarkFont(true)
            .init()
    }
}