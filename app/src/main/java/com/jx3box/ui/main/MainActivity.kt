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

package com.jx3box.ui.main

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.jx3box.R
import com.jx3box.mvvm.base.BaseActivity
import com.jx3box.utils.createLicence
import com.jx3box.utils.getSpValue
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author carey
 * @date 2020/09/17
 */
class MainActivity : BaseActivity() {
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun initData() {
        val isFirst = getSpValue("firstRun", true)
        if (isFirst)
            AlertDialog.Builder(this).create().createLicence()
    }

    override fun initView() {
        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    }

    override fun initImmersionBar() {}

}