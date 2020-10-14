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

package com.jx3box.data.net.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * @author Carey
 * @date 2020/9/30
 */
@Entity(tableName = "UserInfo")
data class UserInfoResult(
    /** UID */
    @PrimaryKey
    @SerializedName("ID")
    val id: Int,
    /** 快递地址 */
    @ColumnInfo(name = "address")
    @SerializedName("address")
    val address: String?,
    /** 设备编码 */
    @ColumnInfo(name = "device_id")
    @SerializedName("device_id")
    val deviceId: String?,
    /** 昵称 */
    @ColumnInfo(name = "display_name")
    @SerializedName("display_name")
    val displayName: String?,
    /** github id */
    @ColumnInfo(name = "github_id")
    @SerializedName("github_id")
    val githubId: Int,
    /** github 昵称 */
    @ColumnInfo(name = "github_name")
    @SerializedName("github_name")
    val githubName: String?,
    /** 服务器 */
    @ColumnInfo(name = "jx3_server")
    @SerializedName("jx3_server")
    val server: String?,
    /** 快递号码 */
    @ColumnInfo(name = "phone")
    @SerializedName("phone")
    val phone: String?,
    /** qq昵称 */
    @ColumnInfo(name = "qq_name")
    @SerializedName("qq_name")
    val qqName: String?,
    /** qq号 */
    @ColumnInfo(name = "qq_number")
    @SerializedName("qq_number")
    val qqNumber: String?,
    /** qq openid */
    @ColumnInfo(name = "qq_openid")
    @SerializedName("qq_openid")
    val qqOpenid: String?,
    /** qq unionid */
    @ColumnInfo(name = "qq_unionid")
    @SerializedName("qq_unionid")
    val qqUnionid: String?,
    /** 头像地址 */
    @ColumnInfo(name = "user_avatar")
    @SerializedName("user_avatar")
    val userAvatar: String?,
    /** 签名 */
    @ColumnInfo(name = "user_bio")
    @SerializedName("user_bio")
    val userBio: String?,
    /** 邮箱 */
    @ColumnInfo(name = "user_email")
    @SerializedName("user_email")
    val userEmail: String?,
    /** 用户组 */
    @ColumnInfo(name = "user_group")
    @SerializedName("user_group")
    val userGroup: Int,
    /** 用户等级 */
    @ColumnInfo(name = "user_level")
    @SerializedName("user_level")
    val userLevel: Int,
    /** 用户绑定的手机号 */
    @ColumnInfo(name = "user_phone")
    @SerializedName("user_phone")
    val userPhone: String?,
    /** 注册时间 */
    @ColumnInfo(name = "user_registered")
    @SerializedName("user_registered")
    val userRegistered: String?,
    /** 0正常,1禁言 */
    @ColumnInfo(name = "user_status")
    @SerializedName("user_status")
    val userStatus: Int,
    /** 资料更新时间 */
    @ColumnInfo(name = "user_updated")
    @SerializedName("user_updated")
    val userUpdated: String?,
    /** 邮箱验证状态 0未验证,1已验证 */
    @ColumnInfo(name = "verify_email")
    @SerializedName("verify_email")
    val verifyEmail: Int,
    /** 微信 昵称 */
    @ColumnInfo(name = "wechat_name")
    @SerializedName("wechat_name")
    val wechatName: String?,
    /** 微信 openid */
    @ColumnInfo(name = "wechat_openid")
    @SerializedName("wechat_openid")
    val wechatOpenid: String?,
    /** 微信unionid */
    @ColumnInfo(name = "wechat_unionid")
    @SerializedName("wechat_unionid")
    val wechatUnionid: String?,
    /** 微博id */
    @ColumnInfo(name = "weibo_id")
    @SerializedName("weibo_id")
    val weiboId: Long?,
    /** 微博昵称 */
    @ColumnInfo(name = "weibo_name")
    @SerializedName("weibo_name")
    val weiboName: String?
)