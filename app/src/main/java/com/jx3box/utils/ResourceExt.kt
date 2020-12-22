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

package com.jx3box.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

/**
 * @author Carey
 * @date 2020/10/29
 */


/**
 * 获取颜色值
 */

fun View.getCompatColor(@ColorRes id: Int): Int {
    return context.getCompatColor(id)
}

fun FragmentActivity.getCompatColor(@ColorRes id: Int): Int {
    return applicationContext.getCompatColor(id)
}

fun Context.getCompatColor(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

/**
 * 获取文本
 */

fun View.getCompatString(@StringRes id: Int): String {
    return context.getCompatString(id)
}

fun View.getCompatString(@StringRes id: Int, vararg str: Any): String {
    return context.getCompatString(id, *str)
}

fun FragmentActivity.getCompatString(@StringRes id: Int): String {
    return applicationContext.getCompatString(id)
}

fun FragmentActivity.getCompatString(@StringRes id: Int, vararg str: Any): String {
    return applicationContext.getCompatString(id, *str)
}

fun Context.getCompatString(@StringRes id: Int): String {
    return resources.getString(id)
}

fun Context.getCompatString(@StringRes id: Int, vararg str: Any): String {
    // *str代表获取vararg中的值 *代表把数组展开
    return resources.getString(id, *str)
}

/**
 * 获取切片资源
 */

fun View.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return context.getCompatDrawable(id)
}

fun FragmentActivity.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return applicationContext.getCompatDrawable(id)
}

fun Context.getCompatDrawable(@DrawableRes id: Int): Drawable? {
    return ContextCompat.getDrawable(this, id)
}

/**
 * 获取尺寸值
 */

fun View.getIntDimen(@DimenRes id: Int): Int {
    return context.getIntDimen(id)
}

fun FragmentActivity.getIntDimen(@DimenRes id: Int): Int {
    return applicationContext.getIntDimen(id)
}

fun Context.getIntDimen(@DimenRes dimen: Int): Int {
    return resources.getDimensionPixelOffset(dimen)
}