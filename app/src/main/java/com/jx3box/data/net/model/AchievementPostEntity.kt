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
import com.jx3box.utils.DateUtil

/**
 * @author Carey
 * @date 2021/1/14
 */
data class AchievementPostEntity(
    @SerializedName("check_remark")
    val checkRemark: String,
    @SerializedName("checked")
    val checked: Int,
    @SerializedName("checked_at")
    val checkedAt: Long,
    @SerializedName("checker_id")
    val checkerId: Any,
    @SerializedName("content")
    val content: String,
    @SerializedName("created")
    val created: String,
    @SerializedName("deleted")
    val deleted: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("level")
    val level: Int,
    @SerializedName("remark")
    val remark: String,
    @SerializedName("source_icon_id")
    val sourceIconId: Int,
    @SerializedName("source_id")
    val sourceId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_nickname")
    val userNickname: String,
    @SerializedName("wordpress_id")
    val wordpressId: Int
) {
    fun getFormatTime(): String {
        return DateUtil.formatDataByTimestamp(DateUtil.YMD_FORMAT, checkedAt)
    }
}