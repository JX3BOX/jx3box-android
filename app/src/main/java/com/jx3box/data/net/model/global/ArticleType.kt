/*
 *    Copyright (c) 2020. jx3box.com
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

package com.jx3box.data.net.model.global

/**
 * 文章类型
 *@author Carey
 *@date  2020/10/4
 */
enum class ArticleType(val type: String) {
//    /**宏库*/
//    MACRO("macro"),

    /**茶馆*/
    BBS("bbs"),

    /**副本*/
    FB("fb"),

    /**插件*/
    JX3DAT("jx3dat"),

    /**职业攻略*/
    BPS("bps"),
//
//    /**捏脸分享*/
//    SHARE("share"),

    /**家园分享*/
    HOUSE("house"),

    /**教程工具*/
    TOOL("tool")
}