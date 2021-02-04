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

data class AchievementRewardEntity(
    @SerializedName("AbradeRate")
    val abradeRate: String,
    @SerializedName("Appearance")
    val appearance: String,
    @SerializedName("AttributeTypes")
    val attributeTypes: List<String>,
    @SerializedName("AucGenre")
    val aucGenre: String,
    @SerializedName("AucSubType")
    val aucSubType: String,
    @SerializedName("BelongSchool")
    val belongSchool: String,
    @SerializedName("BindType")
    val bindType: Int,
    @SerializedName("CanApart")
    val canApart: String,
    @SerializedName("CanChangeMagic")
    val canChangeMagic: String,
    @SerializedName("CanConsume")
    val canConsume: String,
    @SerializedName("CanDestroy")
    val canDestroy: Int,
    @SerializedName("CanEvilCampUse")
    val canEvilCampUse: Int,
    @SerializedName("CanExterior")
    val canExterior: String,
    @SerializedName("CanGoodCampUse")
    val canGoodCampUse: Int,
    @SerializedName("CanNeutralCampUse")
    val canNeutralCampUse: Int,
    @SerializedName("CanSetColor")
    val canSetColor: String,
    @SerializedName("CanShared")
    val canShared: String,
    @SerializedName("CanStack")
    val canStack: Int,
    @SerializedName("CanTrade")
    val canTrade: Int,
    @SerializedName("CanUseInFight")
    val canUseInFight: Int,
    @SerializedName("CanUseOnHorse")
    val canUseOnHorse: Int,
    @SerializedName("CoolDown")
    val coolDown: Int,
    @SerializedName("DescHtml")
    val descHtml: String,
    @SerializedName("DetailType")
    val detailType: String,
    @SerializedName("Diamonds")
    val diamonds: String,
    @SerializedName("Genre")
    val genre: String,
    @SerializedName("GetType")
    val getType: String,
    @SerializedName("IconID")
    val iconId: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("IsEquip")
    val isEquip: Int,
    @SerializedName("IsQuest")
    val isQuest: Int,
    @SerializedName("Level")
    val level: String,
    @SerializedName("MagicKind")
    val magicKind: String,
    @SerializedName("MagicType")
    val magicType: String,
    @SerializedName("MaxDurability")
    val maxDurability: Int,
    @SerializedName("MaxExistAmount")
    val maxExistAmount: String,
    @SerializedName("MaxExistTime")
    val maxExistTime: String,
    @SerializedName("MaxStrengthLevel")
    val maxStrengthLevel: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Price")
    val price: Int,
    @SerializedName("Quality")
    val quality: Int,
    @SerializedName("Recommend")
    val recommend: String,
    @SerializedName("RepairPriceRebate")
    val repairPriceRebate: String,
    @SerializedName("RequireCamp")
    val requireCamp: Int,
    @SerializedName("RequireGender")
    val requireGender: String,
    @SerializedName("RequireLevel")
    val requireLevel: String,
    @SerializedName("Set")
    val set: String,
    @SerializedName("SkillID")
    val skillID: Int,
    @SerializedName("SkillLevel")
    val skillLevel: Int,
    @SerializedName("Source")
    val source: String,
    @SerializedName("SourceID")
    val sourceID: Int,
    @SerializedName("SubType")
    val subType: String,
    @SerializedName("synchronized")
    val synchronized: Int,
    @SerializedName("TypeLabel")
    val typeLabel: String,
    @SerializedName("UiID")
    val uiID: Int,
    @SerializedName("WuCaiHtml")
    val wuCaiHtml: String
) {
    fun getIconUrl(): String {
        return AppConfig.getIconUrl(iconId)
    }
}