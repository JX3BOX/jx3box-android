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

package com.jx3box.utils

import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

/**
 * @author Carey
 * @date 2020/9/17
 */

/**
 * Json字符串转Json对象
 * 该方法可解析List数据,但是该方法解析出来的数据结构为[LinkedTreeMap]
 * 需要获取可操作的数据需要遍历取出map中的value值,太过于麻烦
 * 所以针对于这种情况请使用[fromListJson]
 **/
inline fun <reified T : Any> Gson.fromJson(json: String): T {
    return Gson().fromJson(json, T::class.java)
}

/**
 * Json字符串转List对象
 */
inline fun <reified T> Gson.fromListJson(json: String): T {
    return Gson().fromJson(json, object : TypeToken<T>() {}.type)
}

fun Gson.getJsonRequestBody(params: Any): RequestBody {
    return Gson().toJson(params).toRequestBody("application/json".toMediaTypeOrNull())
}