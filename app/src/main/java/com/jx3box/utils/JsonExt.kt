package com.jx3box.utils

import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken

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