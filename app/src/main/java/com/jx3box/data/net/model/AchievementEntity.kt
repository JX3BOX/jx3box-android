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
import com.jx3box.App
import com.jx3box.R
import com.jx3box.data.net.AppConfig
import com.jx3box.utils.getCompatString

/**
 * @author Carey
 * @date 2021/1/14
 */
data class AchievementEntity(
    @SerializedName("AnnounceType")
    val announceType: String,
    @SerializedName("bDLCOther")
    val bDLCOther: String,
    @SerializedName("BossID")
    val bossId: String,
    @SerializedName("BossName")
    val bossName: String,
    @SerializedName("Counters")
    val counters: String,
    @SerializedName("Desc")
    val desc: String,
    @SerializedName("Detail")
    val detail: String,
    @SerializedName("dwDLCID")
    val dwDLCId: String,
    @SerializedName("dwMapID")
    val dwMapId: String,
    @SerializedName("Exp")
    val exp: String,
    @SerializedName("General")
    val general: String,
    @SerializedName("HolidayID")
    val holidayId: String,
    @SerializedName("ID")
    val id: Int,
    @SerializedName("IconID")
    val iconId: String,
    @SerializedName("IsSplendid")
    val isSplendid: String,
    @SerializedName("Item")
    val item: AchievementRewardEntity?,
    @SerializedName("ItemID")
    val itemID: String,
    @SerializedName("ItemName")
    val itemName: String,
    @SerializedName("ItemType")
    val itemType: String,
    @SerializedName("LayerName")
    val layerName: String,
    @SerializedName("Message")
    val message: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Note")
    val note: String,
    @SerializedName("Point")
    val point: String,
    @SerializedName("post")
    val post: AchievementPostEntity,
    @SerializedName("Postfix")
    val postfix: String,
    @SerializedName("PostfixName")
    val postfixName: String,
    @SerializedName("Prefix")
    val prefix: String,
    @SerializedName("PrefixName")
    val prefixName: String,
    @SerializedName("SceneID")
    val sceneID: String,
    @SerializedName("SceneName")
    val sceneName: String,
    @SerializedName("Series")
    val series: String,
    @SerializedName("SeriesAchievementList")
    val seriesAchievementList: List<SeriesAchievementEntity>,
    @SerializedName("SeriesLevel")
    val seriesLevel: Int,
    @SerializedName("ShiftID")
    val shiftID: String,
    @SerializedName("ShiftType")
    val shiftType: String,
    @SerializedName("ShortDesc")
    val shortDesc: String,
    @SerializedName("ShowGetNew")
    val showGetNew: String,
    @SerializedName("Sub")
    val sub: String,
    @SerializedName("SubAchievementList")
    val subAchievementList: Any,
    @SerializedName("SubAchievements")
    val subAchievements: String,
    @SerializedName("TriggerVal")
    val triggerVal: String,
    @SerializedName("Visible")
    val visible: Int,

    /**
     * 选中的成就子项ID
     */
    var seriesId: Int?
) {
    fun getIconUrl(): String {
        return AppConfig.getIconUrl(iconId)
    }

    fun getTitleName(): String {
        return when {
            postfixName.isNotBlank() -> {
                App.CONTEXT.getCompatString(R.string.postfix_name, postfixName)
            }
            prefixName.isNotBlank() -> {
                App.CONTEXT.getCompatString(R.string.prefix_name, prefixName)
            }
            else -> {
                ""
            }
        }
    }
}