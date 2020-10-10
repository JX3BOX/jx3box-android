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
import android.view.MotionEvent
import android.webkit.WebView
import kotlin.math.abs

/**
 * 自适应高度的webview
 * @author Carey
 * @date 2020/10/06
 */
class CustomWebView(context: Context) : WebView(context) {
    private var startx = 0F
    private var starty = 0F
    private var offsetx = 0F
    private var offsety = 0F
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2, MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, mExpandSpec)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
                startx = event.x
                starty = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                offsetx = abs(event.x - startx)
                offsety = abs(event.y - starty)
                if (offsetx > offsety) {
                    parent.requestDisallowInterceptTouchEvent(true)
                } else {
                    parent.requestDisallowInterceptTouchEvent(false)
                }
            }
        }
        return super.onTouchEvent(event)
    }
}