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

import android.content.Context
import android.os.Bundle
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jx3box.R

/**
 * @author Carey
 * @date 2020/9/24
 */
open class BaseBottomSheetDialog : BottomSheetDialogFragment() {
    private var bottomSheet: FrameLayout? = null
    private var behavior: BottomSheetBehavior<FrameLayout>? = null

    override fun onStart() {
        super.onStart()
        val dialog = dialog as BottomSheetDialog
        bottomSheet =
            dialog.delegate.findViewById(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.apply {
            val params = layoutParams as CoordinatorLayout.LayoutParams
            params.height = this@BaseBottomSheetDialog.getHeight()
            layoutParams = params
            behavior = BottomSheetBehavior.from(this)
            behavior!!.peekHeight = this@BaseBottomSheetDialog.getHeight()
            // 初始为展开状态
            behavior!!.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.BottomDialog)
        isCancelable = true
    }

    open fun getHeight(): Int {
        return resources.displayMetrics.heightPixels
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpValue
     * @return
     */
    protected fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}