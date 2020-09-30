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

package com.jx3box.mvvm.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.carey.module_glidelib.EasyGlide.loadCircleImage
import com.carey.module_glidelib.EasyGlide.loadImage
import com.carey.module_glidelib.R

/**
 * @author Carey
 * @date 2020/9/18
 */
@BindingAdapter("imageUrl", "placeholder", requireAll = false)
fun bindImage(
    imageView: ImageView,
    url: String?,
    placeholder: Int = R.color.transparent
) {
    imageView.loadImage(imageView.context, url, placeholder)
}

@BindingAdapter("circleImgUrl", "placeholder", requireAll = false)
fun bindCircleImage(
    imageView: ImageView,
    url: String?,
    placeholder: Int = R.color.transparent
) {
    imageView.loadCircleImage(imageView.context, url, placeholder)
}