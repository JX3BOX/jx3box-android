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

package com.jx3box.mvvm.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ToastUtils

/**
 * activity 基类
 * @author Carey
 * @date 2020/9/17
 */
abstract class BaseActivity : AppCompatActivity() {
    var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(layoutId)
        initImmersionBar()

        this.initData()
    }

    /**
     * 获取布局ID
     *
     * @return
     */
    protected abstract val layoutId: Int

    /**
     * 数据初始化操作
     */
    protected abstract fun initData()

    /**
     * 初始化view操作
     */
    protected abstract fun initView()

    /**
     * 初始化沉浸状态栏
     */
    protected abstract fun initImmersionBar()

    /**
     * 封装toast方法
     *
     * @param str
     */
    fun showToast(str: String) {
        ToastUtils.showShort(str)
    }

    fun showToast(@StringRes str: Int) {
        ToastUtils.showShort(str)
    }

}