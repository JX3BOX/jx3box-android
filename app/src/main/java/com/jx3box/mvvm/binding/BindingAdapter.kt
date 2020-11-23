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

import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carey.module_glidelib.EasyGlide.loadCircleImage
import com.carey.module_glidelib.EasyGlide.loadImage
import com.jx3box.R
import com.jx3box.data.net.model.global.LabelType
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs


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
    imageView.loadImage(imageView.context, url, isCrossFade = false, placeHolder = placeholder)
}

@BindingAdapter("backgroundRes")
fun bindImage(imageView: ImageView, @DrawableRes res: Int) {
    imageView.setBackgroundResource(res)
}

@BindingAdapter("imageUrl", "subtype", requireAll = false)
fun bindImage(
    imageView: ImageView,
    url: String?,
    subtype: String
) {
    var placeholder = R.color.transparent
    when (subtype) {
        "1" -> placeholder = R.drawable.icon_bbs_experience
        "2" -> placeholder = R.drawable.icon_bbs_memory
        "3" -> placeholder = R.drawable.icon_bbs_media
        "4" -> placeholder = R.drawable.icon_bbs_discuz
        "5" -> placeholder = R.drawable.icon_bbs_idea
    }
    imageView.loadImage(imageView.context, url, isCrossFade = false, placeHolder = placeholder)
}

@BindingAdapter("circleImgUrl", "placeholder", requireAll = false)
fun bindCircleImage(
    imageView: ImageView,
    url: String?,
    placeholder: Int = R.color.transparent
) {
    imageView.loadCircleImage(imageView.context, url, placeholder)
}

@BindingAdapter("adapter")
fun RecyclerView.adapter(adapter: RecyclerView.Adapter<*>) {
    setAdapter(adapter)
}

@BindingAdapter("text", "labels", requireAll = false)
fun TextView.article(text: String, labels: List<String>?) {
    val str = SpannableStringBuilder()
    labels?.run {
        if (isNotEmpty()) {
            for (label in this) {
                try {
                    val labelType = LabelType.valueOf(label.toUpperCase(Locale.getDefault()))
                    val foregroundColorSpan = ForegroundColorSpan(Color.parseColor("#FFFFFF"))
                    val colorSpan = BackgroundColorSpan(Color.parseColor(labelType.color))
                    str.append(" ${labelType.str} ")
                    val startIndex = str.indexOf(" ${labelType.str} ")
                    str.setSpan(
                        colorSpan,
                        startIndex,
                        startIndex + " ${labelType.str} ".length,
                        Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                    )
                    str.setSpan(
                        foregroundColorSpan,
                        startIndex,
                        startIndex + " ${labelType.str} ".length,
                        Spanned.SPAN_INCLUSIVE_EXCLUSIVE
                    )
                    str.append(" ")
                } catch (e: IllegalArgumentException) {
                }
            }
        }
    }
    str.append(text)
    if (str.isEmpty()) str.append(context.resources.getString(R.string.no_title))
    setText(str)
}

@BindingAdapter("date")
fun TextView.showTime(date: String?) {
    date?.run {
        try {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z", Locale.getDefault())
            val d = format.parse(replace("Z", " UTC"))
            val now = System.currentTimeMillis()
            val abs = abs(now - d!!.time)
            val seconds: Long
            when {
                abs < 60000 -> { // 一分钟内
                    seconds = abs / 1000
                    text =
                        context.resources.getString(R.string.a_few_seconds_ago, seconds.toString())
                }
                abs < 3600000 -> { // 一小时内
                    seconds = abs / 60000
                    text =
                        context.resources.getString(R.string.a_few_minutes_ago, seconds.toString())
                }
                abs < 86400000 -> { // 一天内
                    seconds = abs / 3600000
                    text = context.resources.getString(R.string.a_few_hours_ago, seconds.toString())
                }
                abs < 1702967296 -> { // 三十天内
                    seconds = abs / 86400000
                    text = context.resources.getString(R.string.a_few_days_ago, seconds.toString())
                }
                else -> { // 日期格式
                    val df = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    text = df.format(d)
                }
            }
        } catch (e: Exception) {
            text = ""
        }
    }
}