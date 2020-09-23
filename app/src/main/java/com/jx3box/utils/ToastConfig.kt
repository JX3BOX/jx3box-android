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
import android.os.Handler
import android.os.Looper
import android.widget.Toast

object ToastConfig {

    internal var toast: Toast? = null
    internal var onLevel: (Toast.(context: Context, msg: CharSequence, level: Int) -> Unit)? = null
    internal var onToast: (Toast.(context: Context, msg: CharSequence) -> Unit)? = null
    var autoCancel = false

    fun cancel() {
        toast?.cancel()
    }

    /**
     * 全局等级吐司配置
     */
    fun onLevel(block: Toast.(context: Context, msg: CharSequence, level: Int) -> Unit) {
        onLevel = block
    }


    /**
     * 全局吐司的配置
     */
    fun onToast(block: Toast.(context: Context, msg: CharSequence) -> Unit) {
        onToast = block
    }

}


internal fun runMain(block: () -> Unit) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
        block()
    } else {
        Handler(Looper.getMainLooper()).post { block() }
    }
}