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

package com.jx3box

import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader

/**
 * @author Carey
 * @date 2020/9/22
 */
object Flipper {
    /**
     * 初始化Flipper调试器
     */
    fun initFlipper() {
        SoLoader.init(App.CONTEXT, false)
        val client = AndroidFlipperClient.getInstance(App.CONTEXT)
        //Inspector插件
        client.addPlugin(InspectorFlipperPlugin(App.CONTEXT, DescriptorMapping.withDefaults()))
        //SharedPreference插件
        client.addPlugin(SharedPreferencesFlipperPlugin(App.CONTEXT))
        //Database插件
        client.addPlugin(DatabasesFlipperPlugin(App.CONTEXT))
        //CrashReporter插件
        client.addPlugin(CrashReporterPlugin.getInstance())
        //Network插件
        client.addPlugin(NetworkFlipperPlugin())
        client.start()
    }
}