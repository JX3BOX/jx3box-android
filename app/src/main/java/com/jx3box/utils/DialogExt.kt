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

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.jx3box.R
import kotlinx.android.synthetic.main.view_dialog.*
import kotlin.system.exitProcess


/**
 * @author Carey
 * @date 2020/9/22
 */

/**
 * 显示启动时隐私协议弹窗
 */
fun AlertDialog.createLicence() {
    this.show()
    this.setCancelable(false)
    val window = this.window
    window?.apply {
        setContentView(R.layout.view_dialog)
        setGravity(Gravity.CENTER)
        setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val str = context.getString(R.string.launch_dialog)
        val sb = SpannableStringBuilder()
        sb.append(str)

        val startIndex = str.indexOf("《")
        sb.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(context, "隐私政策", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = context.resources.getColor(R.color.color_bf2c2c)
                ds.isUnderlineText = false;

            }
        }, startIndex, startIndex + 6, 0)

        val lastIndex = str.lastIndexOf("《")
        sb.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(context, "用户协议", Toast.LENGTH_SHORT).show()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = context.resources.getColor(R.color.color_bf2c2c)
                ds.isUnderlineText = false

            }
        }, lastIndex, lastIndex + 6, 0)

        tv_content.movementMethod = LinkMovementMethod.getInstance()
        tv_content.setText(sb, TextView.BufferType.SPANNABLE)

        tv_cancel.setOnClickListener {
            this@createLicence.cancel()
            exitProcess(0)
        }

        tv_agree.setOnClickListener {
            context.putSpValue("firstRun", false)
            this@createLicence.cancel()
        }
    }
}