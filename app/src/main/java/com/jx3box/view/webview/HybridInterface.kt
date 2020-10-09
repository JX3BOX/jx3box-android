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
package com.jx3box.view.webview

import android.content.Context
import android.webkit.JavascriptInterface
import com.jx3box.R
import com.jx3box.module_imagebrowser.MNImageBrowser
import com.jx3box.module_imagebrowser.model.ImageBrowserConfig
import com.jx3box.module_log.LogUtils
import com.jx3box.utils.GlideImageEngine
import com.jx3box.utils.toast

/**
 * @author Carey
 * @date 2020/10/7
 */
class HybridInterface(private val context: Context) {
    @JavascriptInterface
    fun getToken(token: String) {
        LogUtils.logD("token>>>>>>>>>>>>>>>>>>>>>>>>$token")
        context.toast(token)
    }

    @JavascriptInterface
    fun showImage(imageUrl: String) {
//        var position = 0
//        val imgs: List<String> = imageUrl.split(",")
//        val imgUrlList: ArrayList<String> = ArrayList()
//        for (s in imgs) {
//            imgUrlList.add(s)
//        }
//        for (i in 0 until imgUrlList.size) {
//            if (img == imgUrlList[i]) {
//                position = i
//            }
//        }
        MNImageBrowser.with(context)
            .setTransformType(ImageBrowserConfig.TransformType.Transform_Default)
            .setIndicatorType(ImageBrowserConfig.IndicatorType.Indicator_Number)
            .setScreenOrientationType(ImageBrowserConfig.ScreenOrientationType.Screenorientation_Default)
            //设置隐藏指示器
            .setIndicatorHide(true)
            //图片引擎
            .setImageEngine(GlideImageEngine())
            //图片集合
//            .setImageList(imgUrlList)
            .setImageUrl(imageUrl)
            //全屏模式
            .setFullScreenMode(true)
            //打开动画
            .setActivityOpenAnime(R.anim.mn_browser_enter_anim)
            //关闭动画
            .setActivityExitAnime(R.anim.mn_browser_exit_anim)
            //手势下拉缩小效果
            .setOpenPullDownGestureEffect(true)
            //显示：传入当前View
            .show(null)
    }
}