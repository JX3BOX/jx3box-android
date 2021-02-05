/*
 *       Copyright (C) 2021.  jx3box.com
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

package com.jx3box.data.net.model


import com.google.gson.annotations.SerializedName
import com.jx3box.data.net.AppConfig

/**
 * @author Carey
 * @date 2021/1/14
 */
data class SeriesAchievementEntity(
    @SerializedName("Detail")
    val detail: String,
    @SerializedName("ID")
    val id: String,
    @SerializedName("IconID")
    val iconId: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("ShortDesc")
    val shortDesc: String,
    @SerializedName("Sub")
    val sub: String,

    var isChecked: Boolean

) {
    fun getIconUrl(): String {
        return AppConfig.getIconUrl(iconId)
    }
}