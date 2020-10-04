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
 * 文章标签类型
 *@author Carey
 *@date  2020/10/4
 */
enum class LabelType(val type: String, val str: String, val color: String) {
    NEWBIE("newbie", "新手易用", "#6f42c1"),
    ADVANCED("advanced", "进阶推荐", "#6f42c1"),
    RECOMMENDED("recommended", "编辑精选", "#6f42c1"),
    GEEK("geek", "骨灰必备", "#6f42c1"),
    RESOLVED("resolved", "已解决", "#49c10f")
}