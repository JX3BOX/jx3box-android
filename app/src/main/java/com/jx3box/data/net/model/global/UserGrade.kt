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
 * 用户级别设定
 *@author Carey
 *@date  2020/10/4
 */
enum class UserGrade(val grade: String) {
    /**游客*/
    VISITOR("0"),

    /**普通用户*/
    GENERAL("1"),

    /**邮箱验证用户*/
    EMAIL("8"),

    /**手机绑定用户*/
    PHONE("16"),

    /**签约作者*/
    AUTHOR("32"),

    /**管理员*/
    MANAGER("64"),

    /**超级管理员*/
    ADMIN("128")
}