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

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    var ID: Int,
    var author: String,
    var color: String,
    var mark: List<String>,
    var meta_1: String,
    var meta_2: String,
    var meta_3: String,
    var meta_4: String,
    var meta_5: String,
    var meta_6: String,
    var meta_7: String,
    var meta_8: String,
    var original: String,
    var post_author: String,
    var post_banner: String,
    var post_content: String,
    var post_date: String,
    var post_excerpt: String,
    var post_meta: PostMeta,
    var post_mode: String,
    var post_modified: String,
    var post_status: String,
    var post_subtype: String,
    var post_title: String,
    var post_type: String,
    var sticky: String
) : Parcelable