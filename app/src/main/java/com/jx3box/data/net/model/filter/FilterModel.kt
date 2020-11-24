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