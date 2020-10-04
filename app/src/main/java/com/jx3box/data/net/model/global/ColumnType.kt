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
 * 栏目类型
 *@author Carey
 *@date  2020/10/4
 */
enum class ColumnType(val type: String) {
    /**成就百科*/
    CJ("cj"),

    /**物品百科*/
    ITEM("item"),

    /**剑三百科*/
    WIKI("wiki"),

    /**题库题目*/
    QUESTION("question"),

    /**题库试卷*/
    PAPER("paper"),

    /**沙雕表情*/
    EMOTION("emotion")
}