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

package com.jx3box.data.net.model

/***
 * @author Carey
 * @date 2020/10/5
 */
data class IndexResult(
    var ID: Int,
    var author: String,
    var bgcolor: String,
    var color: String,
    var created_at: String,
    var desc: String,
    var img: String,
    var link: String,
    var power: String,
    var status: String,
    var title: String,
    var type: String,
    var updated_at: String
)