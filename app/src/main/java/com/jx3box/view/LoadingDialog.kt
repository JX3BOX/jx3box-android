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

package com.jx3box.view

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.Gravity
import com.jx3box.R


/**
 * @author Carey
 * @date 2020/9/30
 */
class LoadingDialog : Dialog {

    constructor(context: Context) : super(context) {}

    constructor(context: Context, theme: Int) : super(context, theme) {}

    private var dialog: LoadingDialog? = null

    fun showDialog(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ): LoadingDialog? {
        dialog = LoadingDialog(context, R.style.LoadingDialog)
        dialog?.run {
            setContentView(R.layout.view_loading)
            setCanceledOnTouchOutside(false)
            setCancelable(cancelable)
            setOnCancelListener(cancelListener)
            window?.apply {
                attributes.gravity = Gravity.CENTER
                val lp = attributes
                lp.dimAmount = 0.2f
                attributes = lp
            }
            show()
        }
        return dialog
    }

    fun dismissDialog() {
        dialog?.run {
            if (isShowing) dismiss()
        }
    }
}