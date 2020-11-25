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

package com.jx3box.data.net.model.filter

import android.content.Context
import com.jx3box.R
import com.jx3box.data.net.model.NormalFilterMenu
import com.jx3box.utils.getCompatString

/**
 * @author Carey
 * @date 2020/11/24
 */
fun Context.getBbsFilterMenu(): List<NormalFilterMenu> {
    val bbsMenu: MutableList<NormalFilterMenu> = ArrayList()
    bbsMenu.add(
        NormalFilterMenu(
            R.drawable.icon_all,
            getCompatString(R.string.filter_all),
            getCompatString(R.string.filter_all_subtitle),
            true
        )
    )
    bbsMenu.add(
        NormalFilterMenu(
            R.drawable.icon_experience,
            getCompatString(R.string.filter_experience),
            getCompatString(R.string.filter_experience_subtitle),
            subType = "1"
        )
    )
    bbsMenu.add(
        NormalFilterMenu(
            R.drawable.icon_memory,
            getCompatString(R.string.filter_memory),
            getCompatString(R.string.filter_memory_subtitle),
            subType = "2"
        )
    )
    bbsMenu.add(
        NormalFilterMenu(
            R.drawable.icon_media,
            getCompatString(R.string.filter_media),
            getCompatString(R.string.filter_media_subtitle),
            subType = "3"
        )
    )
    bbsMenu.add(
        NormalFilterMenu(
            R.drawable.icon_discuz,
            getCompatString(R.string.filter_discuz),
            getCompatString(R.string.filter_discuz_subtitle),
            subType = "4"
        )
    )
    bbsMenu.add(
        NormalFilterMenu(
            R.drawable.icon_feedback,
            getCompatString(R.string.filter_idea),
            getCompatString(R.string.filter_idea_subtitle),
            subType = "5"
        )
    )
    return bbsMenu
}

fun Context.getDiyFaceFilterMenu(): List<NormalFilterMenu> {
    val faceMenu: MutableList<NormalFilterMenu> = ArrayList()
    faceMenu.add(
        NormalFilterMenu(
            R.drawable.icon_all,
            getCompatString(R.string.filter_all),
            getCompatString(R.string.filter_all_subtitle),
            true
        )
    )
    faceMenu.add(
        NormalFilterMenu(
            R.drawable.icon_female,
            getCompatString(R.string.filter_female),
            getCompatString(R.string.filter_female_subtitle),
            subType = getCompatString(R.string.filter_female)
        )
    )
    faceMenu.add(
        NormalFilterMenu(
            R.drawable.icon_male,
            getCompatString(R.string.filter_male),
            getCompatString(R.string.filter_male_subtitle),
            subType = getCompatString(R.string.filter_male)
        )
    )
    faceMenu.add(
        NormalFilterMenu(
            R.drawable.icon_loli,
            getCompatString(R.string.filter_loli),
            getCompatString(R.string.filter_loli_subtitle),
            subType = getCompatString(R.string.filter_loli)
        )
    )
    faceMenu.add(
        NormalFilterMenu(
            R.drawable.icon_boy,
            getCompatString(R.string.filter_boy),
            getCompatString(R.string.filter_boy_subtitle),
            subType = getCompatString(R.string.filter_boy)
        )
    )
    return faceMenu
}

fun Context.getToolsFilterMenu(): List<NormalFilterMenu> {
    val toolsMenu: MutableList<NormalFilterMenu> = ArrayList()
    toolsMenu.add(
        NormalFilterMenu(
            R.drawable.icon_all,
            "全部",
            getCompatString(R.string.filter_all_subtitle),
            true
        )
    )
    toolsMenu.add(
        NormalFilterMenu(
            R.drawable.icon_all,
            "工具源码",
            getCompatString(R.string.filter_all_subtitle),
            subType = "1"
        )
    )
    toolsMenu.add(
        NormalFilterMenu(
            R.drawable.icon_female,
            "资源分享",
            getCompatString(R.string.filter_female_subtitle),
            subType = "2"
        )
    )
    toolsMenu.add(
        NormalFilterMenu(
            R.drawable.icon_male,
            "插件指南",
            getCompatString(R.string.filter_male_subtitle),
            subType = "3"
        )
    )
    toolsMenu.add(
        NormalFilterMenu(
            R.drawable.icon_loli,
            "帮助文档",
            getCompatString(R.string.filter_loli_subtitle),
            subType = "4"
        )
    )
    return toolsMenu
}