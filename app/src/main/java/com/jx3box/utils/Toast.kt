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

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner


fun Context.toast(msg: Int) {
    ToastConfig.toast?.cancel()
    showDefault(getString(msg))
}


fun FragmentActivity.toast(msg: Int) {
    lifeEnabled()
    applicationContext.toast(msg)
}


fun Fragment.toast(msg: Int) {
    lifeEnabled()
    context?.toast(msg)
}

fun Context.toast(msg: CharSequence) {
    ToastConfig.toast?.cancel()
    showDefault(msg)
}

fun FragmentActivity.toast(msg: CharSequence) {
    lifeEnabled()
    applicationContext.toast(msg)
}

fun Fragment.toast(msg: CharSequence) {
    lifeEnabled()
    context?.toast(msg)
}

/**
 * 在函数[ToastConfig.onToast]通过参数[level]来判断返回不同的View
 */
@SuppressLint("ShowToast")
fun Context.toast(msg: CharSequence, level: Int) {

    ToastConfig.toast?.cancel()

    runMain {
        val toast = Toast(this).apply {
            ToastConfig.onLevel?.invoke(this, this@toast, msg, level)

            if (view == null) {
                view = Toast.makeText(this@toast, msg, Toast.LENGTH_SHORT).view
            }
        }
        ToastConfig.toast = toast
        ToastConfig.toast?.show()
    }
}

fun FragmentActivity.toast(msg: CharSequence, level: Int) {
    lifeEnabled()
    applicationContext.toast(msg, level)
}

fun Fragment.toast(msg: CharSequence, level: Int) {
    lifeEnabled()
    context?.toast(msg, level)
}

/**
 * 配置吐司
 */
fun Context.toast(config: Toast.(Context) -> Unit) {

    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = Toast(this).apply {
            config(this@toast)
        }
        ToastConfig.toast?.show()
    }
}

fun FragmentActivity.toast(config: Toast.(Context) -> Unit) {
    lifeEnabled()
    applicationContext.toast(config)
}

fun Fragment.toast(config: Toast.(Context) -> Unit) {
    context?.toast(config)
}

fun Context.longToast(msg: Int) {
    longToast(getString(msg))
}

fun FragmentActivity.longToast(msg: Int) {
    lifeEnabled()
    applicationContext.longToast(msg)
}

fun Fragment.longToast(msg: Int) {
    lifeEnabled()
    context?.longToast(msg)
}

fun Context.longToast(msg: CharSequence) {
    showDefault(msg, false)
}

fun FragmentActivity.longToast(msg: CharSequence) {
    lifeEnabled()
    applicationContext.longToast(msg)
}

fun Fragment.longToast(msg: CharSequence) {
    lifeEnabled()
    context?.longToast(msg)
}


/**
 * 显示常用默认的吐司或者全局设置的吐司样式
 *
 * @param msg 吐司内容
 * @param short 消息停留时间间隔
 */
@SuppressLint("ShowToast")
private fun Context?.showDefault(msg: CharSequence, short: Boolean = true) {
    this ?: return
    ToastConfig.toast?.cancel()

    runMain {
        ToastConfig.toast = if (ToastConfig.onToast != null) {
            Toast(this).apply {
                duration = if (short) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
                ToastConfig.onToast?.invoke(this, this@showDefault, msg)
                if (view == null) {
                    view = Toast.makeText(this@showDefault, msg, Toast.LENGTH_SHORT).view
                }
            }
        } else Toast.makeText(this, msg, Toast.LENGTH_SHORT)

        ToastConfig.toast?.show()
    }
}

private fun LifecycleOwner.lifeEnabled() {
    if (ToastConfig.autoCancel) {
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_PAUSE) {
                    ToastConfig.cancel()
                }
            }
        })
    }
}